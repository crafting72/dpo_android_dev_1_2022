package com.example.m16_architecture.domain

import com.example.m16_architecture.data.UsefulActivitiesRepository
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(
    private val mainRepository: UsefulActivitiesRepository)
{

    suspend fun execute() = mainRepository.getUsefulActivity()
}