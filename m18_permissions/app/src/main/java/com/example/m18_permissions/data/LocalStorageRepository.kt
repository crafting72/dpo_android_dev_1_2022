package com.example.m18_permissions.data

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