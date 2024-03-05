package com.example.homework1_7.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.homework1_7.model.FirstEntity


@Dao
interface CameraDao {

    @Query("SELECT*FROM camera")
    suspend fun getAll(): List<FirstEntity>

    @Insert
    suspend fun insertCamera(camera: FirstEntity)

    @Query("DELETE FROM camera")
    suspend fun clearAll()
}