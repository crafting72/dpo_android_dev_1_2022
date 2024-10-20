package com.example.m15_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.m15_room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordsDao = (application as App).db.wordsDao()
                return MainViewModel(wordsDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            val text = binding.textInputEditText.text.toString()
            if (!text.matches(Regex("[a-z|а-я|A-Я|A-Z|-]+"))) binding.textInputLayout.error =
                getString(R.string.errorText)
            else {
                viewModel.onAddBtn(text)
                binding.textInputLayout.error = ""
            }

        }

        binding.clear.setOnClickListener {
            //(application as App).db.clearAllTables()
            viewModel.onDelBtn()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.allWords
                .collect{ words ->
                    binding.textView
                        .text = words.joinToString(
                        separator = "\r\n"
                    )
                }
        }
    }
}