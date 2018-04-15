package com.mattchowning.todo_mvp.repository

import android.arch.lifecycle.LiveData
import com.mattchowning.todo_mvp.room.TaskItem

interface RepositorySource {

    val allTasks: LiveData<List<TaskItem>>

    fun insertItems(vararg taskItems: TaskItem)
}