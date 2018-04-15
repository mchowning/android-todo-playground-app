package com.mattchowning.todo_mvp.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.mattchowning.todo_mvp.repository.Repository.Source.LOCAL_DB
import com.mattchowning.todo_mvp.repository.Repository.Source.USE_REMOTE
import com.mattchowning.todo_mvp.room.TaskItem

class Repository(context: Context) : RepositorySource {

    private enum class Source {
        USE_REMOTE,
        LOCAL_DB
    }
    private val source = USE_REMOTE

    private val repositorySource = when (source) {
        LOCAL_DB -> LocalDbSource(context)
        USE_REMOTE -> ServerSource()
    }

    override val allTasks: LiveData<List<TaskItem>> = repositorySource.allTasks

    override fun insertItems(vararg taskItems: TaskItem) {
        repositorySource.insertItems(*taskItems)
    }
}