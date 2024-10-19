package com.example.m12_mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Error)
    var state = _state.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()


    fun onButtonClick(text: String?){
        viewModelScope.launch {
            _state.value = State.Loading
            delay(5_000)
            _state.value = State.Success(text)
            _error.send(text!!)
        }
    }

    fun onEditText(text: String?){
        viewModelScope.launch {
            if (text!!.length >= 3) _state.value = State.Success(text)
            else _state.value = State.Error
        }
    }
}