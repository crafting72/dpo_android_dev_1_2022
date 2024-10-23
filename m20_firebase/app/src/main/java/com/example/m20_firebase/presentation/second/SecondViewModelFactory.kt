package com.example.m20_firebase.presentation.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class SecondViewModelFactory @Inject constructor(private val secondViewModel: SecondViewModel)
    : ViewModelProvider.Factory
{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondViewModel::class.java)) {
            return secondViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}