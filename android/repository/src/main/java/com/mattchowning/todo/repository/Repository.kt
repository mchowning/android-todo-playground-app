package com.mattchowning.todo.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.mattchowning.todo.repository.room.TaskItem

interface Repository {

  val allTasks: LiveData<List<TaskItem>>

  fun insertItems(vararg taskItems: TaskItem)

  companion object {
    fun get(context: Context): Repository = RepositorySwitcher(context)
  }
}