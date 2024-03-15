package com.example.homework1_7.domain.usecases

import androidx.lifecycle.LiveData
import com.example.homework1_7.data.Resource
import com.example.homework1_7.domain.repositories.SecondRepository
import com.example.homework1_7.model.SecondEntity
import com.example.homework1_7.model.SecondModel
import javax.inject.Inject

class GetSecondUseCase @Inject constructor(private val doorsRepository: SecondRepository) {
    fun getDoors():LiveData<Resource<SecondModel>> = doorsRepository.getDoors()

    suspend fun getDBDoors(): List<SecondEntity> = doorsRepository.getDBDoors()
    suspend fun insert(doorEntity: SecondEntity) = doorsRepository.insert(doorEntity)
    suspend fun clearAll() = doorsRepository.clearAll2()

    suspend fun deleteDoor(doorEntity: SecondEntity) = doorsRepository.deleteDoor(doorEntity)

}