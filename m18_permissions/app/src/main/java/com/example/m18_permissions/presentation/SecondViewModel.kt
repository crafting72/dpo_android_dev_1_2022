package com.example.m18_permissions.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m18_permissions.data.SightPhotosDB
import com.example.m18_permissions.domain.AddSightPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class SecondViewModel@Inject constructor(
    private val addSightPhotosUseCase: AddSightPhotosUseCase
) : ViewModel() {

    fun savePhoto(uri: String, name: String){
        viewModelScope.launch(Dispatchers.IO) {
            addSightPhotosUseCase.addSightPhotos(
                SightPhotosDB(
                    date = name,
                    uri = uri
                )
            )
        }
    }


}