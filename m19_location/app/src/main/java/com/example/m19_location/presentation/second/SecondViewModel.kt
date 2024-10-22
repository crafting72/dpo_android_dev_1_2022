package com.example.m19_location.presentation.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m19_location.data.room.SightPhotosDB
import com.example.m19_location.domain.sightPhotosUseCase.AddSightPhotosUseCase
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