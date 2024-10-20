package com.example.m17_recyclerview.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class MarsPhotosDataSource @Inject constructor() {

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val searchMarsPhotosApi: SearchMarsPhotosApi = retrofit.create(SearchMarsPhotosApi::class.java)

    interface SearchMarsPhotosApi{
        @GET("/mars-photos/api/v1/rovers/curiosity/photos/")
        suspend fun getMarsPhotos(
         //   @Query("page") page: Int = 1,
            @Query("api_key") api_key: String = API_KEY,
            @Query("earth_date") earth_date: String = EARTH_DATE
        ): MarsPhotosDto
    }

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"
        private const val API_KEY = "B08RZxUoZ8bMtug9fYhwUI0erWvq6w1qjfkTCVOL"
        private const val EARTH_DATE = "2022-1-1"
    }
}

//https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2022-1-1&api_key=B08RZxUoZ8bMtug9fYhwUI0erWvq6w1qjfkTCVOL