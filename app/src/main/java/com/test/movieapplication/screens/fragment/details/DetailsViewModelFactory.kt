package com.test.movieapplication.screens.fragment.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailsViewModelFactory(
//    private val filmsRealizationDatabase: FilmsRealizationDatabase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(
//            filmsRealizationDatabase = filmsRealizationDatabase
        ) as T
    }

}