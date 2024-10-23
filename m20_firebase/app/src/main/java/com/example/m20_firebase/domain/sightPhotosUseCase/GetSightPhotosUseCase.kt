package com.example.m20_firebase.domain.sightPhotosUseCase

import com.example.m20_firebase.data.room.LocalStorageRepository
import javax.inject.Inject

class GetSightPhotosUseCase @Inject constructor(
    private val mainRepository: LocalStorageRepository
) {

    fun getAllSightPhotos() = mainRepository.getPhoto()
}