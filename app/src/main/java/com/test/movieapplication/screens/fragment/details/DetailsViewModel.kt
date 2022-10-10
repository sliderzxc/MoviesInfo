package com.test.movieapplication.screens.fragment.details

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.movieapplication.R
import com.test.movieapplication.database.model.ResultDatabaseModel
import com.test.movieapplication.database.repository.FilmsRepositoryDatabase
import com.test.movieapplication.databinding.FragmentDetailsBinding
import com.test.movieapplication.utils.mapper.toResultDatabaseModel
import com.test.movieapplication.utils.other.IsExist
import com.test.movieapplication.utils.viewmodel.shared.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun isFilmExistInDatabase(id: Int, binding: FragmentDetailsBinding, context: Context?) {
            viewModelScope.launch {
                when (checkFilmState(id)) {
                    IsExist.EXIST -> {
                        withContext(Dispatchers.Main) {
                            binding.btnAddFilmToFavorite.setImageDrawable(
                                context?.let { context -> ContextCompat.getDrawable(context, R.drawable.icon_like) }
                            )
                            changeStateIsChecked(true)
                        }
                    }
                    IsExist.NOT_EXIST -> {
                        withContext(Dispatchers.Main) {
                            binding.btnAddFilmToFavorite.setImageDrawable(
                                context?.let { context -> ContextCompat.getDrawable(context, R.drawable.icon_unlike) }
                            )
                            changeStateIsChecked(false)
                        }
                    }
                    IsExist.NONE -> { throw Exception("None in Details Fragmentw") }
                }
            }
    }

    fun onClickButtonFavoriteListener(binding: FragmentDetailsBinding, context: Context?, sharedViewModel: SharedViewModel) {
        if (stateIsChecked.value == false) {
            binding.btnAddFilmToFavorite.setImageDrawable(
                context?.let { it: Context -> ContextCompat.getDrawable(it, R.drawable.icon_like) }
            )
            changeStateIsChecked(true)
            sharedViewModel.result.value?.toResultDatabaseModel()?.let { insertToFavoriteTable(resultDatabaseModel = it) }
        } else {
            binding.btnAddFilmToFavorite.setImageDrawable(
                context?.let { it: Context -> ContextCompat.getDrawable(it, R.drawable.icon_unlike) }
            )
            changeStateIsChecked(false)
            sharedViewModel.result.value?.toResultDatabaseModel()?.let { deleteFromFavoriteTable(resultDatabaseModel = it) }
        }
    }

    private suspend fun checkFilmState(id: Int): IsExist {
        return when (filmsRepositoryDatabase.isExistFilmInDatabase(id)) {
            1 -> {
                IsExist.EXIST
            }
            0 -> {
                IsExist.NOT_EXIST
            }
            else -> {
                IsExist.NONE
            }
        }
    }

}