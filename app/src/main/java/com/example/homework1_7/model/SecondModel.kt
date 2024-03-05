package com.example.homework1_7.model

data class SecondModel(
    val `data`: List<Data>,
    val success: Boolean
){
    data class Data(
        val favorites: Boolean,
        val id: Int,
        val name: String,
        val room: String,
        val snapshot: String
    )
}
