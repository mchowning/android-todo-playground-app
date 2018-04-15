package com.mattchowning.todo.repository

import android.arch.lifecycle.LiveData
import com.mattchowning.todo.repository.room.TaskItem

internal interface RepositorySource {

    val allTasks: LiveData<List<TaskItem>>

    fun insertItems(vararg taskItems: TaskItem)
}