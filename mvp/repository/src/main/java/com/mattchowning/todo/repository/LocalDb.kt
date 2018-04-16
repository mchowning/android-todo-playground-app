package com.mattchowning.todo.repository

import android.arch.persistence.room.Room
import android.content.Context
import com.mattchowning.todo.repository.room.AppDatabase
import com.mattchowning.todo.repository.room.TaskItem

internal class LocalDb(context: Context) : Repository {

  private val database: AppDatabase =
      Room.databaseBuilder(context, AppDatabase::class.java, "app-database.db")
          .build()

  override val allTasks = database.taskDao().getAllTasks()

  override fun insertItems(vararg taskItems: TaskItem) {
    doInBackground { database.taskDao().insertItems(*taskItems) }
  }

  private fun doInBackground(function: () -> Unit) {
    val runnable = Runnable { function() }
    Thread(runnable).start()
  }
}