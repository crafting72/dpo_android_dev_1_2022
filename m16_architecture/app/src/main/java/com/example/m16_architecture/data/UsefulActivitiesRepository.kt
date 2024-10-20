package com.example.m16_architecture.data

import javax.inject.Inject

class UsefulActivitiesRepository @Inject constructor
    (private val usefulActivityDataSource: UsefulActivityDataSource) {
    suspend fun  getUsefulActivity() = usefulActivityDataSource.searchActivityApi.getActivity()
}