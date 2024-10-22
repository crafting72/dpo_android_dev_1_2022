package com.example.m19_location.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SightPhotosDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sightPhotosDao(): SightPhotosDao
}