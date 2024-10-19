package com.example.m12_mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.m12_mvvm.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private  val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state
                    .collect { state ->
                        when(state){
                            State.Loading -> {
                                binding.progressBar.isVisible = true
                                binding.button.isEnabled = false
                            }
                            is State.Success -> {
                                binding.progressBar.isVisible = false
                                binding.button.isEnabled = true
                                state.data = binding.textInputEditText.text.toString()
                            }
                            State.Error -> {
                                binding.button.isEnabled = false
                            }
                        }
                    }
            }
        binding.textInputEditText.doOnTextChanged { text, _, _, _ ->
            if (viewModel.state.value != State.Loading) viewModel.onEditText(text.toString())
        }
        binding.button.setOnClickListener {
            viewModel.onButtonClick((viewModel.state.value as State.Success).data)
        }
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.error
                    .collect{
                        Snackbar.make(
                            requireView(),
                            getString(R.string.errorText1) + it +
                                    getString(R.string.errorText2),
                            Snackbar.LENGTH_SHORT)
                            .show()
                        viewModel.onEditText(binding.textInputEditText.text.toString())
                    }
            }
    }
}