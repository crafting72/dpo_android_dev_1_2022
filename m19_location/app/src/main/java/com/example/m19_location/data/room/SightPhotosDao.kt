package com.example.m19_location.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SightPhotosDao {

    @Query("SELECT * FROM sight_photo_DB")
    fun getAll(): Flow<List<SightPhotosDB>>

    @Insert(entity = SightPhotosDB::class)
    suspend fun insert(sightPhotosDB: SightPhotosDB)

    @Query("DELETE FROM sight_photo_DB WHERE uri = :uri")
    suspend fun delete(uri: String)
}