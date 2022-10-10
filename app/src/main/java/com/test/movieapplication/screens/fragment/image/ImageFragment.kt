package com.test.movieapplication.screens.fragment.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.test.movieapplication.app.App
import com.test.movieapplication.databinding.FragmentImageBinding
import com.test.movieapplication.network.model.Result
import com.test.movieapplication.utils.other.MainConstants
import com.test.movieapplication.utils.viewmodel.shared.SharedViewModel

class ImageFragment : Fragment() {
    private val binding by lazy { FragmentImageBinding.inflate(layoutInflater) }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        inject()
        onBackButtonClickListener()
        sharedViewModel.result.value?.let { getResultFromSharedViewModel(it) }
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun getResultFromSharedViewModel(result: Result) {
        Glide.with(binding.root)
            .load("${MainConstants.IMAGE_BASE_URL}${result.poster_path}")
            .into(binding.image)
    }

    private fun onBackButtonClickListener() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}