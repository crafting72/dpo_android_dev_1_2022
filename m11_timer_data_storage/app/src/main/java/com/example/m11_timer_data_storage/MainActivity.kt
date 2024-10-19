package com.example.m11_timer_data_storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m11_timer_data_storage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    internal lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = Repository(this)
        binding.textView.text = repository.getText()
        binding.buttonClear.setOnClickListener {
            repository.clearText()
            binding.editText.text = null
            binding.textView.text = ""
        }
        binding.buttonSave.setOnClickListener {
            repository.saveText(binding.editText.text.toString())
            binding.textView.text = repository.getText()
        }
    }
}