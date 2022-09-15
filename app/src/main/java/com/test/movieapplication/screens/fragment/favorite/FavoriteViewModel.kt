package com.test.movieapplication.screens.fragment.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.movieapplication.database.model.ResultDatabaseModel
import com.test.movieapplication.database.repository.FilmsRepositoryDatabase

class FavoriteViewModel(
    val filmsRepositoryDatabase: FilmsRepositoryDatabase
) : ViewModel() {

    fun getAllFavoriteFilms() : LiveData<List<ResultDatabaseModel>>{
        return filmsRepositoryDatabase.getAllFavoriteFilms()
    }


}