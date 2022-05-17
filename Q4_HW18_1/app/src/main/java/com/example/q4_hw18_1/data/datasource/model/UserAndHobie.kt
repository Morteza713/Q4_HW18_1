package com.example.q4_hw18_1.data.datasource.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class UserAndHobie(
    @Embedded val user: User,
    @Relation(parentColumn = "id", entityColumn = "user_id")
    val hobie: List<Hobie>
) {
}