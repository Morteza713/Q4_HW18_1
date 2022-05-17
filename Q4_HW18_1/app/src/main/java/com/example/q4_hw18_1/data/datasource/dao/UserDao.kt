package com.example.q4_hw18_1.data.datasource.dao

import androidx.room.*
import com.example.q4_hw18_1.data.datasource.model.Hobie
import com.example.q4_hw18_1.data.datasource.model.User
import com.example.q4_hw18_1.data.datasource.model.UserAndHobie
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHobbies(hobbies: List<Hobie>)

    @Query("SELECT * FROM user_table ")
    fun getUsers(): Flow<List<UserAndHobie>>

    @Query("DELETE FROM user_table WHERE id=:id")
    suspend fun removeUser(id: String)

    @Update
    suspend fun updateUser(user: User)
}