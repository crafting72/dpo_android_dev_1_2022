package com.example.m17_recyclerview.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m17_recyclerview.data.MarsPhotosDto
import com.example.m17_recyclerview.domain.GetMarsPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMarsPhotosUseCase: GetMarsPhotosUseCase) : ViewModel()
{
   /* private val _action = MutableStateFlow("")
    var action = _action.asStateFlow() */

    private val _marsPhoto = MutableStateFlow(MarsPhotosDto(emptyList()))
    val marsPhotos = _marsPhoto.asStateFlow()

    init {
        loadMarsPhoto()
    }

    private fun loadMarsPhoto(){
        viewModelScope.launch(Dispatchers.IO){
            kotlin.runCatching {
                getMarsPhotosUseCase.execute()
            }.fold(
                onSuccess = { _marsPhoto.value = it },
                onFailure = { Log.d("MainViewModel", it.message ?: "") }
            )
        }
    }

   /* suspend fun reloadUsefulActivity() {
        _action.value = getMarsPhotosUseCase.execute().activity
    } */
}