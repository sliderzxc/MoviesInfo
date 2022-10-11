package com.test.movieapplication.screens.fragment.main

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.test.movieapplication.databinding.FragmentMainBinding
import com.test.movieapplication.paging.repository.FilmsRepositoryPaging

class MainViewModel(
    filmRepositoryPaging: FilmsRepositoryPaging
) : ViewModel() {

    var list = filmRepositoryPaging.getFilms().cachedIn(viewModelScope)

    fun observeInternetConnection(isConnected: Boolean, binding: FragmentMainBinding) {
        if (isConnected) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.bottomNavigationView.visibility = View.VISIBLE
            binding.connectionLost.ivConnectionLost.visibility = View.GONE
            binding.connectionLost.tvConnectionLost.visibility = View.GONE
            binding.connectionLost.tvConnectionLostInfo.visibility = View.GONE
        } else {
            binding.recyclerView.visibility = View.GONE
            binding.bottomNavigationView.visibility = View.GONE
            binding.connectionLost.ivConnectionLost.visibility = View.VISIBLE
            binding.connectionLost.tvConnectionLost.visibility = View.VISIBLE
            binding.connectionLost.tvConnectionLostInfo.visibility = View.VISIBLE
        }
    }

}