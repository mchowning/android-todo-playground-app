package com.mattchowning.todo_mvp.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "task_table")
data class TaskItem(val title: String,
                    val description : String,
                    @PrimaryKey val uniqueId : String = UUID.randomUUID().toString())
