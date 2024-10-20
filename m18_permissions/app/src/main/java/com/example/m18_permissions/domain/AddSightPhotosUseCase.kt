package com.example.m18_permissions.domain

import com.example.m18_permissions.data.LocalStorageRepository
import com.example.m18_permissions.data.SightPhotosDB
import javax.inject.Inject

class AddSightPhotosUseCase @Inject constructor(
    private val mainRepository: LocalStorageRepository
) {

    suspend fun addSightPhotos(sightPhotosDB: SightPhotosDB){
        mainRepository.savePhoto(sightPhotosDB)
    }
}