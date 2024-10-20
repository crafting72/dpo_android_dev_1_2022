package com.example.m17_recyclerview.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.m17_recyclerview.data.PhotosDto
import com.example.m17_recyclerview.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels { mainViewModelFactory }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val marsPhotosAdapter = MarsPhotosAdapter{onItemClick(it)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.adapter = marsPhotosAdapter

        viewModel.marsPhotos.onEach {
            marsPhotosAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onItemClick(item: PhotosDto){
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToSecondFragment(
            item.img_src
        ))
    }
}