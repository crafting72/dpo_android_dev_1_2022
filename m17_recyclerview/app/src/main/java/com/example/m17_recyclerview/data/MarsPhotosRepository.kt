package com.example.m17_recyclerview.data

import javax.inject.Inject

class MarsPhotosRepository @Inject constructor
    (private val marsPhotosDataSource: MarsPhotosDataSource) {
    suspend fun  getMarsPhotos() = marsPhotosDataSource.searchMarsPhotosApi.getMarsPhotos()
}