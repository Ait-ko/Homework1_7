package com.example.homework1_7.data

import androidx.lifecycle.LiveData
import com.example.homework1_7.base.BaseRepository
import com.example.homework1_7.domain.repositories.FirstRepository
import com.example.homework1_7.domain.repositories.SecondRepository
import com.example.homework1_7.model.FirstEntity
import com.example.homework1_7.model.FirstModel
import com.example.homework1_7.model.SecondEntity
import com.example.homework1_7.model.SecondModel
import javax.inject.Inject


class Repository @Inject constructor(private val api: AppApiService, private val db: AppDatabase) :
    BaseRepository(), FirstRepository, SecondRepository {
    override fun getCameras(): LiveData<Resource<FirstModel>> = apiRequest {
        api.getCameras().body()!!
    }

    override fun getDoors(): LiveData<Resource<SecondModel>> = apiRequest {
        api.getDoors().body()!!
    }


    override suspend fun getDBCameras(): List<FirstEntity> = db.cameraDao().getAll()

    override suspend fun clearAll() = db.cameraDao().clearAll()
    override suspend fun deleteCamera(firstEntity: FirstEntity) {

    }


    override suspend fun insert(cameraEntity: FirstEntity) =
        db.cameraDao().insertCamera(cameraEntity)

    override suspend fun deleteDoor(secondEntity: SecondEntity) {
        TODO("Not yet implemented")
    }


    override suspend fun insert(doorEntity: SecondEntity) = db.doorDao().insertDoor(doorEntity)

    override suspend fun getDBDoors(): List<SecondEntity> = db.doorDao().getAll()
    override suspend fun clearAll2() = db.doorDao().clearAll()




}