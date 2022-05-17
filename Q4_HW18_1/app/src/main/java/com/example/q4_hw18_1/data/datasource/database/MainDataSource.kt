package com.example.q4_hw18_1.data.datasource.database

import com.example.q4_hw18_1.data.datasource.dao.UserDao
import com.example.q4_hw18_1.data.datasource.model.Hobie
import com.example.q4_hw18_1.data.datasource.model.User
import com.example.q4_hw18_1.data.datasource.model.UserAndHobie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainDataSource @Inject constructor(private val userDao: UserDao) {

    fun getUsers(): Flow<List<UserAndHobie>> = userDao.getUsers()
    suspend fun addUser(user: User) = userDao.addUser(user)
    suspend fun removeUser(id: String) = userDao.removeUser(id)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    suspend fun addHobbies(hobbies: List<Hobie>) = userDao.addHobbies(hobbies)
}