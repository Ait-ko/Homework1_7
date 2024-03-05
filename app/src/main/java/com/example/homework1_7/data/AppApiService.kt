package com.example.homework1_7.data

import com.example.homework1_7.model.FirstModel
import com.example.homework1_7.model.SecondModel

import retrofit2.Response
import retrofit2.http.GET

interface AppApiService {
    @GET("cameras/")
    suspend fun getCameras(): Response<FirstModel>

    @GET("doors/")
    suspend fun getDoors(): Response<SecondModel>
}