package com.example.myapplication.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class MovieEntity(
    @field:ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @field:ColumnInfo(name="tittle")
    val title:String,
    @field:ColumnInfo(name="date")
    val date:String,
)
