package com.example.m1_hello_world

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.m1_hello_world.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var counter = 0
    private val maxCounter = 50
    private val minCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.plus.setOnClickListener {
            if (counter != maxCounter) {
                counter++
                if (counter < maxCounter) {
                    if (counter == minCounter + 1) {
                        binding.minus.isEnabled = true
                        binding.EqualTo0.visibility = View.INVISIBLE
                        binding.lessThan50.visibility = View.VISIBLE
                    }
                } else if (counter == maxCounter) {
                    binding.lessThan50.visibility = View.INVISIBLE
                    binding.equalTo50.visibility = View.VISIBLE
                    binding.reset.visibility = View.VISIBLE
                }
                changeCounter(counter, binding)
            }
        }
        binding.minus.setOnClickListener {
            if (counter != minCounter) {
                counter--
                if (counter == minCounter) {
                    equalTo0(binding)
                } else if (counter < maxCounter) {
                    if (counter == maxCounter - 1) {
                        binding.lessThan50.visibility = View.VISIBLE
                        minus(binding)
                    }
                }
                changeCounter(counter, binding)
            }
        }
        binding.reset.setOnClickListener {
            equalTo0(binding)
            counter = minCounter
            changeCounter(counter, binding)
        }
    }

    private fun minus(binding: ActivityMainBinding) {
        binding.equalTo50.visibility = View.INVISIBLE
        binding.reset.visibility = View.INVISIBLE
    }

    private fun equalTo0(binding: ActivityMainBinding) {
        binding.minus.isEnabled = false
        binding.EqualTo0.visibility = View.VISIBLE
        binding.lessThan50.visibility = View.INVISIBLE
        minus(binding)
    }

    private fun changeCounter(counter: Int, binding: ActivityMainBinding) {
        binding.account.text = counter.toString()
        binding.lessThan50.text = getString(R.string.places_left) + ' ' + (50 - counter).toString()
    }
}