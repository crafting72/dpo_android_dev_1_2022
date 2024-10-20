package com.example.m16_architecture.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

private const val BASE_URL = "https://www.boredapi.com/"

class UsefulActivityDataSource @Inject constructor() {

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val searchActivityApi: SearchActivityApi = retrofit.create(SearchActivityApi::class.java)

    interface SearchActivityApi{
        @GET("/api/activity/")
        suspend fun getActivity(): UsefulActivityDto
    }
}