package com.example.q4_hw18_1.data.datasource.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName =  "user_table")
data class User(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String,
    val nationalCode: String
): Parcelable {}