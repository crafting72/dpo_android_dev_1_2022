package com.example.views_and_animations.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.views_and_animations.clock.TimeState
import com.example.views_and_animations.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val listenerText: (TimeState) -> Unit = {
        val time = it.time
        val hour = time / (1000 * 60 * 60) % 24
        val minute = time / (1000 * 60) % 60
        val second = time / 1000 % 60
        binding.textView.text= String.format("%02d: %02d: %02d", hour, minute, second)
    }
    private val listenerButton: (TimeState) -> Unit = {
        if (it.isPlayed){
            binding.buttonStop.visibility = View.VISIBLE
            binding.buttonStart.visibility = View.INVISIBLE
        }
        else {
            binding.buttonStop.visibility = View.INVISIBLE
            binding.buttonStart.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customAnalogClock.addUpdateListener(listenerText)
        binding.customAnalogClock.addUpdateListener(listenerButton)
        binding.buttonStart.setOnClickListener { binding.customAnalogClock.start() }
        binding.buttonStop.setOnClickListener { binding.customAnalogClock.stop() }
        binding.buttonReset.setOnClickListener { binding.customAnalogClock.reset() }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.customAnalogClock.removeListener(listenerText)
        binding.customAnalogClock.removeListener(listenerButton)
        _binding = null
    }
}