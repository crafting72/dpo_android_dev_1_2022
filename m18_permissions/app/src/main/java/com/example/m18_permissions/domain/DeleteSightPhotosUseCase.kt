package com.example.m18_permissions.domain

import com.example.m18_permissions.data.LocalStorageRepository
import javax.inject.Inject

class DeleteSightPhotosUseCase @Inject constructor(
    private val mainRepository: LocalStorageRepository
) {

    suspend fun deleteSightPhotos(uri: String){
        mainRepository.deletePhoto(uri)
    }

}