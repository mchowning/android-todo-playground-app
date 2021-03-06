package com.mattchowning.todo.repository.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
internal interface TaskDao {

  @Query("SELECT * from task_table")
  fun getAllTasks(): LiveData<List<TaskItem>>

  @Insert(onConflict = REPLACE)
  fun insertItems(vararg taskItem: TaskItem)
}