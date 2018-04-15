package com.mattchowning.todo.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.mattchowning.todo.repository.room.TaskItem

class Repository(context: Context) : RepositorySource {

    private enum class Source {
        USE_REMOTE,
        LOCAL_DB
    }
    private val source = Source.USE_REMOTE

    private val repositorySource = when (source) {
        Source.LOCAL_DB -> LocalDbSource(context)
        Source.USE_REMOTE -> ServerSource()
    }

    override val allTasks: LiveData<List<TaskItem>> = repositorySource.allTasks

    override fun insertItems(vararg taskItems: TaskItem) {
        repositorySource.insertItems(*taskItems)
    }
}