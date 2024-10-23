package com.example.views_and_animations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.views_and_animations.databinding.ActivityMainBinding
import com.example.views_and_animations.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}