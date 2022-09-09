package com.test.movieapplication.screens.fragment.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.test.movieapplication.paging.FilmsRepositoryPaging
import javax.inject.Inject

class MainViewModel(
    filmRepositoryPaging: FilmsRepositoryPaging
) : ViewModel() {

    var list = filmRepositoryPaging.getFilms().cachedIn(viewModelScope)

}