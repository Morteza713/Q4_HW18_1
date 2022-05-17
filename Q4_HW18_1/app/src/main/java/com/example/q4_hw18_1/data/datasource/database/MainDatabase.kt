package com.example.q4_hw18_1.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.q4_hw18_1.data.datasource.dao.UserDao
import com.example.q4_hw18_1.data.datasource.model.Hobie
import com.example.q4_hw18_1.data.datasource.model.User

@Database(entities = [User::class, Hobie::class], version = 1, exportSchema = true)
abstract class MainDatabase:RoomDatabase() {

    abstract fun UserDao(): UserDao

}