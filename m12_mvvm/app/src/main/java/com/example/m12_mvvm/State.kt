package com.example.m12_mvvm

sealed class State {
    data object Loading : State()
    data class Success(var data: String?) : State()
    data object Error : State()
}