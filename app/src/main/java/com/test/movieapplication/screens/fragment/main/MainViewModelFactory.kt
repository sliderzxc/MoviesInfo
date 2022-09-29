package com.test.movieapplication.screens.fragment.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.movieapplication.paging.repository.FilmsRepositoryPaging

class MainViewModelFactory(
    private val filmsRepositoryPaging: FilmsRepositoryPaging
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            filmRepositoryPaging = filmsRepositoryPaging
        ) as T
    }

}