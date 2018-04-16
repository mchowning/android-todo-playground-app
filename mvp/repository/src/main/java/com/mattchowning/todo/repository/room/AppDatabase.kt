package com.mattchowning.todo.repository.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [TaskItem::class],
          version = 1,
          exportSchema = false)
internal abstract class AppDatabase : RoomDatabase() {
  abstract fun taskDao(): TaskDao
}