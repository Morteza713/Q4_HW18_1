package com.example.q4_hw18_1.data.network.remote

import com.example.q4_hw18_1.data.network.model.UserItem
import com.example.q4_hw18_1.data.network.model.UserItemList
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiUser {
    @GET("users")
    suspend fun getUsers(): Response<List<UserItemList>>

    @GET("users/{id}")
    suspend fun getUserListById(@Path("id") id: String): Response<UserItemList>

    @POST("users")
    suspend fun createAccount(@Body userData: UserItem): Response<String>

    @Multipart
    @POST("users/{id}/image")
    suspend fun uploadPic(@Path("id") id: String, @Part image: MultipartBody.Part): Response<Any>
}