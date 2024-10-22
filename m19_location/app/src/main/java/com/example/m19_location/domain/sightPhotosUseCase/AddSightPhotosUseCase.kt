package com.example.m19_location.domain.sightPhotosUseCase

import com.example.m19_location.data.room.LocalStorageRepository
import com.example.m19_location.data.room.SightPhotosDB
import javax.inject.Inject

class AddSightPhotosUseCase @Inject constructor(
    private val mainRepository: LocalStorageRepository
) {

    suspend fun addSightPhotos(sightPhotosDB: SightPhotosDB){
        mainRepository.savePhoto(sightPhotosDB)
    }
}