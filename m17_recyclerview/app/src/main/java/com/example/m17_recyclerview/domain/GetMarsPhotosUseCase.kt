package com.example.m17_recyclerview.domain

import com.example.m17_recyclerview.data.MarsPhotosRepository
import javax.inject.Inject

class GetMarsPhotosUseCase @Inject constructor(
    private val mainRepository: MarsPhotosRepository)
{

    suspend fun execute() = mainRepository.getMarsPhotos()
}