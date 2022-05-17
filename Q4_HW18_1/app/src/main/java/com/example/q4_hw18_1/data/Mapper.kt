package com.example.q4_hw18_1.data

import com.example.q4_hw18_1.data.datasource.model.Hobie
import com.example.q4_hw18_1.data.network.model.UserItemList
import kotlin.random.Random

object Mapper {

    fun mapInHobie(userItem: UserItemList): List<Hobie> {
        return userItem.hobbies.map {
            Hobie(Random.nextInt(), it, userItem._id)
        }
    }

}




























//fun transformToUserResponse(user: User, hobbies: List<Hobie>): UserItemList {
//        return UserItemList(
//            _id = user.id,
//            firstName = user.firstName,
//            lastName = user.lastName,
//            nationalCode = user.nationalCode,
//            hobbies.map {
//                it.name
//            },
//            ""
//        )
//    }