package com.example.m20_firebase.domain.sightPhotosUseCase

import com.example.m20_firebase.data.room.LocalStorageRepository
import javax.inject.Inject

class DeleteSightPhotosUseCase @Inject constructor(
    private val mainRepository: LocalStorageRepository
) {

    suspend fun deleteSightPhotos(uri: String){
        mainRepository.deletePhoto(uri)
    }

}