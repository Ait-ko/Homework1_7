package com.example.homework1_7.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "door")
data class SecondEntity(
    val favorites: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val name: String,
    val room: String,
    val snapshot: String
): Serializable

