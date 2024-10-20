package com.example.m18_permissions.domain

import com.example.m18_permissions.data.LocalStorageRepository
import javax.inject.Inject

class GetSightPhotosUseCase @Inject constructor(
    private val mainRepository: LocalStorageRepository
) {

    fun getAllSightPhotos() = mainRepository.getPhoto()
}