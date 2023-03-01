package com.example.bottomnavigationwork.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bottomnavigationwork.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}