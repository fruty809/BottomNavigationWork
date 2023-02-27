package com.example.bottomnavigationwork.ui.data.local.room

import androidx.room.*
import com.example.bottomnavigationwork.ui.model.Task

@Dao
interface TaskDao {


    @Query("SELECT * FROM task ORDER BY id  DESC")
    fun getAll(): List<Task>


    /*СОХРАНЯЕТ task*/
    @Insert
    fun insert(task: Task)

    /* УДАЛЯЕТ task*/
    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}