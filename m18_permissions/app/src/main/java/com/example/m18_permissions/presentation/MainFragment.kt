package com.example.m18_permissions.presentation

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.m18_permissions.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.FileNotFoundException
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels { mainViewModelFactory }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val sightPhotosAdapter = SightPhotosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycleView.adapter = sightPhotosAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.sightPhotos.collect {photos ->
                photos.forEach { photo ->
                    try {
                        MediaStore.Images.Media.getBitmap(
                            requireContext().contentResolver,
                            Uri.parse(photo.uri)
                        )
                    }catch (e: FileNotFoundException){
                        viewModel.deleteSightPhoto(photo.uri)
                    }
                }
                sightPhotosAdapter.submitList(photos)
            }
        }

        binding.button.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToSecondFragment())
        }

    }
}
