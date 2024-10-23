package com.example.m20_firebase.data.room

import javax.inject.Inject

class LocalStorageRepository @Inject constructor(private val sightPhotosDao: SightPhotosDao) {

    suspend fun savePhoto(sightPhotosDB: SightPhotosDB){
        sightPhotosDao.insert(sightPhotosDB)
    }

    fun getPhoto() = sightPhotosDao.getAll()

    suspend fun deletePhoto(uri: String){
        sightPhotosDao.delete(uri)
    }

}