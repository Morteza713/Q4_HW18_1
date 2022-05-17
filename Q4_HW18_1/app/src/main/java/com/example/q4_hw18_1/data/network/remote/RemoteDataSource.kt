package com.example.q4_hw18_1.data.network.remote

import com.example.q4_hw18_1.data.network.model.UserItem
import com.example.q4_hw18_1.data.network.model.UserItemList
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiUser: ApiUser) {

    suspend fun getUsers(): Response<List<UserItemList>> = apiUser.getUsers()
    suspend fun getUserListById(id: String) = apiUser.getUserListById(id)
    suspend fun createAccount(user: UserItem) = apiUser.createAccount(user)
    suspend fun uploadPic(id: String, image: MultipartBody.Part) = apiUser.uploadPic(id, image)

}