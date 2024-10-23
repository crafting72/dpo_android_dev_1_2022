package com.example.m20_firebase.presentation.second

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m20_firebase.App
import com.example.m20_firebase.R
import com.example.m20_firebase.data.room.SightPhotosDB
import com.example.m20_firebase.domain.sightPhotosUseCase.AddSightPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

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

    @SuppressLint("MissingPermission")
    fun createNotification(context: Context, contentText: String) {
        val intent = Intent(context, SecondFragment::class.java)

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        else
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        val notification = NotificationCompat.Builder(context, App.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle("You took a photo")
            .setContentText(contentText)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(Random.nextInt(), notification)
    }
}