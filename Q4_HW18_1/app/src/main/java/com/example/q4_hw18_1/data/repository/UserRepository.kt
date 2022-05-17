package com.example.q4_hw18_1.data.repository

import com.example.q4_hw18_1.data.Mapper
import com.example.q4_hw18_1.data.Result
import com.example.q4_hw18_1.data.datasource.database.MainDataSource
import com.example.q4_hw18_1.data.datasource.model.User
import com.example.q4_hw18_1.data.datasource.model.UserAndHobie
import com.example.q4_hw18_1.data.network.model.UserItem
import com.example.q4_hw18_1.data.network.model.UserItemList
import com.example.q4_hw18_1.data.network.remote.RemoteDataSource
import com.example.q4_hw18_1.data.resultState
import com.example.q4_hw18_1.di.StateIoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    @StateIoDispatcher
    private val dispatcher: CoroutineDispatcher,
    private val remoteDataSource: RemoteDataSource,
    private val mainDataSource: MainDataSource,

    ) {
    suspend fun getUsers(): Flow<Result<List<UserItemList>>> {
        return resultState(dispatcher) { remoteDataSource.getUsers() }
    }

    suspend fun getUserListById(id: String): Flow<Result<UserItemList>> {
        return resultState(dispatcher) { remoteDataSource.getUserListById(id) }
    }

    suspend fun createUser(user: UserItem): Response<String> {
        return remoteDataSource.createAccount(user)
    }

    suspend fun uploadPic(id: String, image: MultipartBody.Part) {
        remoteDataSource.uploadPic(id, image)
    }

    suspend fun addUser(user: User) {
        withContext(Dispatchers.IO) {
            mainDataSource.addUser(user)
        }
    }

    suspend fun addHobbies(id: String) {
        withContext(Dispatchers.IO) {
            val user = remoteDataSource.getUserListById(id)
            val hobbies = Mapper.mapInHobie(user.body()!!)
            mainDataSource.addHobbies(hobbies)
        }
    }

    fun getUsersFromDataBase(): Flow<List<UserAndHobie>> {
        return mainDataSource.getUsers()
    }

    suspend fun removeUser(id: String) {
        withContext(Dispatchers.IO) {
            mainDataSource.removeUser(id)
        }
    }


}



































//suspend fun updateUser(user: User) {
//    withContext(Dispatchers.IO) {
//        mainDataSource.updateUser(user)
//    }
//}