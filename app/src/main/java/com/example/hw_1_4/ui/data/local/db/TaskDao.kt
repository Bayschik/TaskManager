package com.example.hw_1_4.ui.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hw_1_4.ui.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY title ASC")
    fun getAll():List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

}