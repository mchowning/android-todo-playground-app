package com.mattchowning.todo_mvp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.mattchowning.todo_mvp.LocalServerService
import com.mattchowning.todo_mvp.room.TaskItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class ServerSource : RepositorySource {

    private val localMutable = MutableLiveData<List<TaskItem>>()
    override val allTasks: LiveData<List<TaskItem>> = localMutable

    companion object {
        private val service: LocalServerService = Retrofit.Builder()
                .baseUrl(LocalServerService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(LocalServerService::class.java)
    }

    init {
        service.getItems().enqueue(object : Callback<List<TaskItem>> {

            override fun onResponse(call: Call<List<TaskItem>>?,
                                    response: Response<List<TaskItem>>?) {
                localMutable.value = response?.body()
            }

            override fun onFailure(call: Call<List<TaskItem>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    override fun insertItems(vararg taskItems: TaskItem) {
        taskItems.forEach {
            service.insertItem(it)
                    .enqueue(object : Callback<List<TaskItem>> {

                        override fun onResponse(call: Call<List<TaskItem>>?,
                                                response: Response<List<TaskItem>>?) {
                            Timber.d("received insert response: ${response?.body()}")
                            localMutable.value = response?.body()
                        }

                        override fun onFailure(call: Call<List<TaskItem>>?, t: Throwable?) {
                            Timber.e("received insert error: $t")
                            t?.printStackTrace()
                        }
                    })
        }
    }
}