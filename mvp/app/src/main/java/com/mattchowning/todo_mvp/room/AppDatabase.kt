package com.mattchowning.todo_mvp.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [ TaskItem::class ],
          version = 1,
          exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}