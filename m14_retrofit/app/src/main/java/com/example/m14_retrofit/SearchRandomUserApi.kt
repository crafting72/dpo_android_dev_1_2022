package com.example.m14_retrofit

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://randomuser.me"

object RetrofitServices {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchRandomUserApi: SearchRandomUserApi = retrofit.create(SearchRandomUserApi::class.java)
}

interface SearchRandomUserApi{
    @GET("/api/")
    suspend fun getRandomUser(): RandomUserModel
}