package com.mattchowning.todo.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.mattchowning.todo.repository.room.TaskItem

internal class RepositorySwitcher(context: Context) : Repository {

  private enum class Source {
    USE_REMOTE,
    LOCAL_DB
  }

  private val source = Source.LOCAL_DB

  private val repositorySource = when (source) {
    Source.LOCAL_DB   -> LocalDb(context)
    Source.USE_REMOTE -> Server()
  }

  override val allTasks: LiveData<List<TaskItem>> = repositorySource.allTasks

  override fun insertItems(vararg taskItems: TaskItem) {
    repositorySource.insertItems(*taskItems)
  }
}
