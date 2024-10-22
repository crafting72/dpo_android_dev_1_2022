package com.example.m19_location.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m19_location.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.cacheDir.deleteRecursively()
    }
}