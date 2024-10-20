package com.example.m18_permissions.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m18_permissions.data.SightPhotosDB
import com.example.m18_permissions.domain.DeleteSightPhotosUseCase
import com.example.m18_permissions.domain.GetSightPhotosUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

//@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSightPhotosUseCase: GetSightPhotosUseCase,
    private val deleteSightPhotosUseCase: DeleteSightPhotosUseCase) : ViewModel()
{

    val sightPhotos = this.getSightPhotosUseCase.getAllSightPhotos().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    suspend fun deleteSightPhoto(uri: String){
        deleteSightPhotosUseCase.deleteSightPhotos(uri)
    }
}
