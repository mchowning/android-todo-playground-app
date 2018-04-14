package com.mattchowning.todo_mvp

import android.arch.persistence.room.Room
import android.content.Context
import com.mattchowning.todo_mvp.Repository.Source.*
import com.mattchowning.todo_mvp.room.AppDatabase
import com.mattchowning.todo_mvp.room.TaskItem
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repository(context: Context) {

    private enum class Source {
        USE_REMOTE,
        OFFLINE_ONLY
    }

    companion object {

        private val SOURCE = OFFLINE_ONLY

        private val service: LocalServerService = Retrofit.Builder()
                .baseUrl(LocalServerService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(LocalServerService::class.java)
    }

    private val database: AppDatabase = Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    "app-database.db")
                    .build()

    val allTasks = database.taskDao().getAllTasks()

    init {

        when (SOURCE) {
            USE_REMOTE -> {
                TODO("Implement me!")
//                service.getItems().enqueue(object : Callback<List<TaskItem>> {
//
//                    override fun onResponse(call: Call<List<TaskItem>>?,
//                                            response: Response<List<TaskItem>>?) {
//                        data.value = response?.body()
//                    }
//
//                    override fun onFailure(call: Call<List<TaskItem>>?,
//                                           t: Throwable?) {
//                        t?.printStackTrace()
//                    }
//                })
            }
            OFFLINE_ONLY -> {

            }

        }
    }

    fun insertItem(taskItem: TaskItem) {
        when(SOURCE) {
            OFFLINE_ONLY ->  database.taskDao().insertItem(taskItem)
            USE_REMOTE ->    TODO("not implemented")
        }
    }
}