package com.example.q4_hw18_1.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItem(
    val firstName: String?,
    val lastName: String?,
    val nationalCode: String?,
    val hobbies: ArrayList<String>
): Parcelable {
}