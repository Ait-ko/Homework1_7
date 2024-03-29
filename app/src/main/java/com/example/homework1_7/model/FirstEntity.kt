package com.example.homework1_7.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "camera")
data class FirstEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int ?=null,
    val favorites: Boolean?=false,
    val name: String?="",
    val rec: Boolean?=false,
    val room: String?="",
    val snapshot: String?=""
):Serializable
