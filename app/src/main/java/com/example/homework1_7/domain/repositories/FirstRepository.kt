package com.example.homework1_7.domain.repositories

import androidx.lifecycle.LiveData
import com.example.homework1_7.model.FirstEntity
import com.example.homework1_7.model.FirstModel
import com.example.homework1_7.data.Resource



interface FirstRepository {
    fun getCameras(): LiveData<Resource<FirstModel>>
   suspend fun  getDBCameras():List<FirstEntity>
   suspend fun clearAll()
   suspend fun deleteCamera(firstEntity: FirstEntity)

   suspend fun insert(firstEntity: FirstEntity)
}