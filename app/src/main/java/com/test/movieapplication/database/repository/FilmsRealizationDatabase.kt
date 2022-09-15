package com.test.movieapplication.database.repository

import androidx.lifecycle.LiveData
import com.test.movieapplication.database.dao.FilmsDaoDatabase
import com.test.movieapplication.database.model.ResultDatabaseModel
import javax.inject.Inject

class FilmsRealizationDatabase @Inject constructor(
    private val filmsDaoDatabase: FilmsDaoDatabase
) : FilmsRepositoryDatabase {

    override suspend fun insertToFavoriteTable(resultDatabaseModel: ResultDatabaseModel) {
        filmsDaoDatabase.insertToFavoriteTable(resultDatabaseModel = resultDatabaseModel)
    }

    override suspend fun deleteFromFavoriteTable(resultDatabaseModel: ResultDatabaseModel) {
        filmsDaoDatabase.deleteFromFavoriteTable(resultDatabaseModel = resultDatabaseModel)
    }

    override fun getAllFavoriteFilms(): LiveData<List<ResultDatabaseModel>> {
        return filmsDaoDatabase.getAllPopular()
    }

    override suspend fun isExistFilmInDatabase(id: Int): Int {
        return filmsDaoDatabase.isExistFilmInDatabase(id)
    }

}