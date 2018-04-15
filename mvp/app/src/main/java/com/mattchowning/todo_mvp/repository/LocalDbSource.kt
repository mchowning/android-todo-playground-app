package com.mattchowning.todo_mvp.repository

import android.arch.persistence.room.Room
import android.content.Context
import com.mattchowning.todo_mvp.room.AppDatabase
import com.mattchowning.todo_mvp.room.TaskItem

class LocalDbSource(context : Context) : RepositorySource {

    private val database: AppDatabase = Room.databaseBuilder(context,
            AppDatabase::class.java,
            "app-database.db")
            .build()

    override val allTasks = database.taskDao().getAllTasks()

    override fun insertItems(vararg taskItems: TaskItem) {
        doInBackground { database.taskDao().insertItems(*taskItems) }
    }

    private fun doInBackground(function: () -> Unit) {
        val runnable = Runnable { function() }
        Thread(runnable).start()
        // dispose of subscription?
//        Completable.fromCallable(function)
//                .subscribeOn(Schedulers.io())
//                .subscribe(object : CompletableObserver {
//                    override fun onComplete() {}
//                    override fun onSubscribe(d: Disposable) {}
//                    override fun onError(e: Throwable) { e.printStackTrace() }
//                })
    }
}