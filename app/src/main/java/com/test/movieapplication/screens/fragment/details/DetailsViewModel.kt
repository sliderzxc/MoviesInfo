package com.test.movieapplication.screens.fragment.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.movieapplication.database.model.ResultDatabaseModel

class DetailsViewModel: ViewModel() {

    private val _stateIsChecked = MutableLiveData<Boolean>(false)
    var stateIsChecked: LiveData<Boolean> = _stateIsChecked

    fun changeStateIsChecked(boolean: Boolean) {
        _stateIsChecked.value = boolean
    }

    fun clickButtonAddToFavorite(resultDatabaseModel: ResultDatabaseModel, isFavorite: Boolean) {
//        resultDatabaseModel.isFavorite = !isFavorite
//        filmsRealizationDatabase.insertToFavoriteTable(resultDatabaseModel)
    }

}