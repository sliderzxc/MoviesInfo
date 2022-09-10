package com.test.movieapplication.screens.fragment.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.test.movieapplication.R
import com.test.movieapplication.app.App
import com.test.movieapplication.databinding.FragmentDetailsBinding
import com.test.movieapplication.network.model.Result
import com.test.movieapplication.utils.other.Constants
import com.test.movieapplication.utils.viewmodel.SharedViewModel

class DetailsFragment : Fragment() {
    private val binding by lazy { FragmentDetailsBinding.inflate(layoutInflater) }

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val detailsViewModel: DetailsViewModel by activityViewModels()

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
        sharedViewModel.result.observe(activity as LifecycleOwner) {
            getResultAndBindToDetailsFragment(
                it
            )
        }
        onClickButtonFavoriteListener()
        onClickPoster()
        onBackButtonClickListener()
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun getResultAndBindToDetailsFragment(result: Result) {
        binding.movieTitle.text = result.title
        binding.dateStatus.text = result.release_date
        binding.textVote.text = result.vote_average.toString()
        Glide.with(binding.root)
            .load("${Constants.IMAGE_BASE_URL}${result.poster_path}")
            .into(binding.posterImage)
        Glide.with(binding.root)
            .load("${Constants.IMAGE_BASE_URL}${result.backdrop_path}")
            .into(binding.imageBackdrop)
    }

    private fun onClickPoster() {
        binding.posterImage.setOnClickListener {
            val direction = DetailsFragmentDirections.actionFromDetailsFragmentToImageFragment()
            findNavController().navigate(direction)
        }
    }

    private fun onBackButtonClickListener() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onClickButtonFavoriteListener() {
        binding.btnAddFilmToFavorite.setOnClickListener {
            if (detailsViewModel.stateIsChecked.value == false) {
                binding.btnAddFilmToFavorite.setImageDrawable(
                    context?.let { context -> ContextCompat.getDrawable(context, R.drawable.icon_like) }
                )
                detailsViewModel.changeStateIsChecked(true)
            } else {
                binding.btnAddFilmToFavorite.setImageDrawable(
                    context?.let { context -> ContextCompat.getDrawable(context, R.drawable.icon_unlike) }
                )
                detailsViewModel.changeStateIsChecked(false)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detailsViewModel.changeStateIsChecked(false)
    }

}