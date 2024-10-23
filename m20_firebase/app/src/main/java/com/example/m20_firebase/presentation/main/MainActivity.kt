package com.example.m20_firebase.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.m20_firebase.R
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("registration token", it.result)
        }
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.cacheDir.deleteRecursively()
    }
}