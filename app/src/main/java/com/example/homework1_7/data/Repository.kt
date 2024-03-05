package com.example.homework1_7.data

import androidx.lifecycle.LiveData
import com.example.homework1_7.base.BaseRepository
import com.example.homework1_7.model.FirstModel
import com.example.homework1_7.model.SecondModel
import javax.inject.Inject

class Repository @Inject constructor(private val api: AppApiService) : BaseRepository(api) {
    fun getCameras(): LiveData<Resource<FirstModel>> = apiRequest {
        api.getCameras().body()!!
    }
    fun getDoors(): LiveData<Resource<SecondModel>> = apiRequest {
        api.getDoors().body()!!
    }

}