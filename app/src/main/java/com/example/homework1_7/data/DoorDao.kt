package com.example.homework1_7.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.homework1_7.model.SecondEntity

@Dao
interface DoorDao {

    @Query("SELECT*FROM door")
    suspend fun getAll(): List<SecondEntity>

    @Insert
    suspend fun insertDoor(camera: SecondEntity)

    @Query("DELETE FROM door")
    suspend fun clearAll()
}