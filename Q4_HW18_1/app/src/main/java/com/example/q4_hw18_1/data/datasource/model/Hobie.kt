package com.example.q4_hw18_1.data.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hobie_table")
data class Hobie(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "user_id")
    val userId:String
){}
