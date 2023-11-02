package com.example.hw_1_4.ui.Task

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val title:String? = null,
    val desc:String? = null
):Serializable
