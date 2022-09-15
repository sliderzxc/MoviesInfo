package com.test.movieapplication.screens.fragment.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.movieapplication.database.model.ResultDatabaseModel
import com.test.movieapplication.database.repository.FilmsRepositoryDatabase
import com.test.movieapplication.utils.other.IsExist
import kotlinx.coroutines.launch

class DetailsViewModel(
    val filmsRepositoryDatabase: FilmsRepositoryDatabase
) : ViewModel() {

    private val _stateIsChecked = MutableLiveData(false)
    var stateIsChecked: LiveData<Boolean> = _stateIsChecked

    fun changeStateIsChecked(boolean: Boolean) {
        _stateIsChecked.value = boolean
    }

    fun insertToFavoriteTable(resultDatabaseModel: ResultDatabaseModel) {
        viewModelScope.launch {
            filmsRepositoryDatabase.insertToFavoriteTable(resultDatabaseModel = resultDatabaseModel)
        }
    }

    fun deleteFromFavoriteTable(resultDatabaseModel: ResultDatabaseModel) {
        viewModelScope.launch {
            filmsRepositoryDatabase.deleteFromFavoriteTable(resultDatabaseModel = resultDatabaseModel)
        }
    }

    suspend fun isFilmsExistInDatabase(id: Int): IsExist {
        val result = filmsRepositoryDatabase.isExistFilmInDatabase(id)
        Log.d("MyLog", result.toString())
        return if (result == 1) {
            IsExist.EXIST
        } else if (result == 0){
            IsExist.NOT_EXIST
        } else {
            IsExist.NONE
        }
    }

}