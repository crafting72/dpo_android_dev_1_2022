package com.example.m18_permissions.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SightPhotosDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sightPhotosDao(): SightPhotosDao
}