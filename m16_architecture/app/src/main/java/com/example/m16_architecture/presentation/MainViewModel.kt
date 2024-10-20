package com.example.m16_architecture.presentation

import androidx.lifecycle.ViewModel
import com.example.m16_architecture.domain.GetUsefulActivityUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

//@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsefulActivityUseCase: GetUsefulActivityUseCase) : ViewModel()
{
    private val _action = MutableStateFlow("")
    var action = _action.asStateFlow()

    suspend fun reloadUsefulActivity() {
        _action.value = getUsefulActivityUseCase.execute().activity
    }
}