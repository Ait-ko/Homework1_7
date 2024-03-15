package com.example.homework1_7.domain.repositories

import androidx.lifecycle.LiveData
import com.example.homework1_7.data.Resource
import com.example.homework1_7.model.SecondEntity
import com.example.homework1_7.model.SecondModel

interface SecondRepository {
    fun getDoors(): LiveData<Resource<SecondModel>>

    suspend fun  getDBDoors():List<SecondEntity>
    suspend fun clearAll2()

    suspend fun insert(secondEntity: SecondEntity)
    suspend fun deleteDoor(secondEntity: SecondEntity)
}