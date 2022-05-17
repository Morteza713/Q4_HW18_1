package com.example.q4_hw18_1.data.network.model


data class UserItemList(
    val _id: String,
    val firstName: String,
    val lastName: String,
    val nationalCode: String,
    val hobbies: List<String>,
    val image: String
) {
}