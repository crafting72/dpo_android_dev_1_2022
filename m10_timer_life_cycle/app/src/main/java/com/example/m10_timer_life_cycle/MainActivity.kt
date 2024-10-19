package com.example.m10_timer_life_cycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.m10_timer_life_cycle.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var currentMaxProgressBar = 10
    private var currentProgressBar = currentMaxProgressBar
    private var onTimer = false
    private val myScope = CoroutineScope(Dispatchers.Default + Job())
    private var process: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        savedInstanceState?.let { bundle ->
            binding.startButton.visibility = bundle.getInt(KEY_VISIBILITY_START_BUTTON, View.VISIBLE)
            binding.stopButton.visibility = bundle.getInt(KEY_VISIBILITY_STOP_BUTTON, View.INVISIBLE)
            currentProgressBar = bundle.getInt(KEY_CURRENT_PROGRESS_BAR, currentProgressBar)
            binding.progressBarCircular.progress = currentProgressBar
            binding.slider.isEnabled = bundle.getBoolean(KEY_IS_ENABLED_SLIDER,true)
            currentMaxProgressBar = bundle.getInt(KEY_CURRENT_MAX_PROGRESS_BAR, currentMaxProgressBar)
            onTimer = bundle.getBoolean(KEY_ON_TIMER,onTimer)
        }
        binding.progressBarCircular.max = currentMaxProgressBar
        binding.timer.text = currentProgressBar.toString()
        val timerOff = {
            onTimer = false
            process?.cancel()
            currentProgressBar = currentMaxProgressBar
            binding.startButton.visibility = View.VISIBLE
            binding.stopButton.visibility = View.INVISIBLE
            binding.slider.isEnabled = true
            binding.timer.text = currentMaxProgressBar.toString()
            binding.progressBarCircular.progress = 0
            Toast.makeText(this, getString(R.string.TimerFinish), Toast.LENGTH_SHORT).show()
        }
        val timerOn = {
            onTimer = true
            val currentProgressBarInLaunch = currentProgressBar
            process = myScope.launch(Dispatchers.Main) {
                currentProgressBar = currentProgressBarInLaunch
                binding.timer.text = currentProgressBar.toString()
                binding.progressBarCircular.progress = currentProgressBar
                binding.startButton.visibility = View.INVISIBLE
                binding.stopButton.visibility = View.VISIBLE
                binding.slider.isEnabled = false
                while (currentProgressBar != 0){
                    delay(1000)
                    currentProgressBar -= 1
                    binding.timer.text = currentProgressBar.toString()
                    binding.progressBarCircular.progress = currentProgressBar
                }
                timerOff()
            }
        }
        binding.slider.addOnChangeListener { _, value, _ ->
            currentMaxProgressBar = value.toInt()
            currentProgressBar = currentMaxProgressBar
            binding.timer.text = currentProgressBar.toString()
            binding.progressBarCircular.max = currentProgressBar}
        binding.startButton.setOnClickListener {
            timerOn()
        }
        binding.stopButton.setOnClickListener {
            timerOff()
        }
        if (onTimer){
            timerOn()
        }
        setContentView(binding.root)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        val binding=ActivityMainBinding.inflate(layoutInflater)
        outState.putInt(KEY_CURRENT_MAX_PROGRESS_BAR, currentMaxProgressBar)
        outState.putInt(KEY_CURRENT_PROGRESS_BAR, currentProgressBar)
        outState.putInt(KEY_VISIBILITY_STOP_BUTTON, binding.stopButton.visibility)
        outState.putInt(KEY_VISIBILITY_START_BUTTON, binding.startButton.visibility)
        outState.putBoolean(KEY_IS_ENABLED_SLIDER, binding.slider.isEnabled)
        outState.putBoolean(KEY_ON_TIMER, onTimer)
        process?.cancel()
        super.onSaveInstanceState(outState)
    }
}