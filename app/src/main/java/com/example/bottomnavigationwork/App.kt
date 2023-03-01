package com.example.bottomnavigationwork

import android.app.Application
import androidx.room.Room
import com.example.bottomnavigationwork.data.local.room.AppDataBase

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }


    companion object {
        lateinit var db: AppDataBase

    }
}