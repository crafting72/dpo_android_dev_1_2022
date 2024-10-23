package com.example.m20_firebase.domain.sightPhotosUseCase

import com.example.m20_firebase.data.room.LocalStorageRepository
import com.example.m20_firebase.data.room.SightPhotosDB
import javax.inject.Inject

class AddSightPhotosUseCase @Inject constructor(
    private val mainRepository: LocalStorageRepository
) {

    suspend fun addSightPhotos(sightPhotosDB: SightPhotosDB){
        mainRepository.savePhoto(sightPhotosDB)
    }
}