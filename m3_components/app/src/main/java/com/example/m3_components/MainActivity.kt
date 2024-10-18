package com.example.m3_components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.m3_components.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var currentMaxProgressBar = 10
        val myScope = CoroutineScope(Dispatchers.Default + Job())
        var process: Job? = null
        val timerOff = {
            process?.cancel()
            binding.startButton.visibility = View.VISIBLE
            binding.stopButton.visibility = View.INVISIBLE
            binding.slider.isEnabled = true
            binding.timer.text = currentMaxProgressBar.toString()
            binding.progressBarCircular.progress = 0
            Toast.makeText(this, getString(R.string.TimerFinish), Toast.LENGTH_SHORT).show()
        }
        val timerOn = {
            process = myScope.launch(Dispatchers.Main) {
                binding.startButton.visibility = View.INVISIBLE
                binding.stopButton.visibility = View.VISIBLE
                binding.slider.isEnabled = false
                for (i in currentMaxProgressBar - 1 downTo 0) {
                    delay(1000)
                    binding.timer.text = i.toString()
                    binding.progressBarCircular.progress = (currentMaxProgressBar - i)
                }
                timerOff()
            }
        }
        binding.slider.addOnChangeListener { _, value, _ ->
            currentMaxProgressBar = value.toInt()
            binding.timer.text = currentMaxProgressBar.toString()
            binding.progressBarCircular.max = currentMaxProgressBar}
        binding.startButton.setOnClickListener {
            timerOn()
        }
        binding.stopButton.setOnClickListener {
            timerOff()
        }
    }
}