package com.example.homework1_7.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework1_7.model.FirstEntity
import com.example.homework1_7.model.SecondEntity

@Database(entities = [FirstEntity::class, SecondEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cameraDao(): CameraDao
    abstract fun doorDao(): DoorDao
}