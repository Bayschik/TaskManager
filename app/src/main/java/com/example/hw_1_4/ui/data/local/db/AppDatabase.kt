package com.example.hw_1_4.ui.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw_1_4.ui.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}