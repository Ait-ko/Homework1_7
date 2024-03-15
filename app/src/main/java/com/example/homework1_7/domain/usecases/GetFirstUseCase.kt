package com.example.homework1_7.domain.usecases

import androidx.lifecycle.LiveData
import com.example.homework1_7.data.Resource
import com.example.homework1_7.domain.repositories.FirstRepository
import com.example.homework1_7.model.FirstEntity
import com.example.homework1_7.model.FirstModel

import javax.inject.Inject

class GetFirstUseCase @Inject constructor(private val camerasRepository: FirstRepository) {
    fun getCameras(): LiveData<Resource<FirstModel>> = camerasRepository.getCameras()

    suspend fun getDBCameras(): List<FirstEntity> = camerasRepository.getDBCameras()

    suspend fun clearAll() = camerasRepository.clearAll()

    suspend fun deleteCamera(cameraEntity: FirstEntity) = camerasRepository.deleteCamera(cameraEntity)

    suspend fun insert(cameraEntity: FirstEntity) = camerasRepository.insert(cameraEntity)
}