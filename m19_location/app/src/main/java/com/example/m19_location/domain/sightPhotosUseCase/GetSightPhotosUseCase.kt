package com.example.m19_location.domain.sightPhotosUseCase

import com.example.m19_location.data.room.LocalStorageRepository
import javax.inject.Inject

class GetSightPhotosUseCase @Inject constructor(
    private val mainRepository: LocalStorageRepository
) {

    fun getAllSightPhotos() = mainRepository.getPhoto()
}