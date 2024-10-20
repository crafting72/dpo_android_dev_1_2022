package com.example.m18_permissions.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m18_permissions.R
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