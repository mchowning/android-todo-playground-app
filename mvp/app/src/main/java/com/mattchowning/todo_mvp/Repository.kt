package com.mattchowning.todo_mvp

import android.arch.lifecycle.MutableLiveData
import com.mattchowning.todo_mvp.Repository.Source.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private enum class Source {
        SERVER,
        LOCAL_STATIC
    }

    val data = MutableLiveData<List<TaskItem>>()

    init {
        when (SOURCE) {
            SERVER -> {
                service.getItems().enqueue(object : Callback<List<TaskItem>> {

                    override fun onResponse(call: Call<List<TaskItem>>?,
                                            response: Response<List<TaskItem>>?) {
                        data.value = response?.body()
                    }

                    override fun onFailure(call: Call<List<TaskItem>>?,
                                           t: Throwable?) {
                        t?.printStackTrace()
                    }
                })
            }
            LOCAL_STATIC -> {
                data.value = LOCAL_INITIAL_DATA
            }

        }
    }

    companion object {

        private val SOURCE = LOCAL_STATIC

        private val LOCAL_INITIAL_DATA = listOf(
                TaskItem("name1", "description1"),
                TaskItem("name2", "description2"),
                TaskItem("name3", "description3"))

        private val service: LocalServerService = Retrofit.Builder()
                .baseUrl(LocalServerService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(LocalServerService::class.java)
    }

    fun addItem(taskItem: TaskItem) {
        when(SOURCE) {
            LOCAL_STATIC -> data.value = data.value?.plus(taskItem)
            SERVER ->       TODO("not implemented")
        }
    }
}