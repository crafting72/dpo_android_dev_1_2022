package com.example.m19_location.domain.sightPhotosUseCase

import com.example.m19_location.data.room.LocalStorageRepository
import javax.inject.Inject

class DeleteSightPhotosUseCase @Inject constructor(
    private val mainRepository: LocalStorageRepository
) {

    suspend fun deleteSightPhotos(uri: String){
        mainRepository.deletePhoto(uri)
    }

}