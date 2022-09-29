package com.test.movieapplication.screens.fragment.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.test.movieapplication.paging.repository.FilmsRepositoryPaging

class MainViewModel(
    filmRepositoryPaging: FilmsRepositoryPaging
) : ViewModel() {

    var list = filmRepositoryPaging.getFilms().cachedIn(viewModelScope)

}