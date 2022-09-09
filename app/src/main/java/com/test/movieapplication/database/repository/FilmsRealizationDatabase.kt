package com.test.movieapplication.database.repository

import androidx.lifecycle.LiveData
import com.test.movieapplication.database.dao.FilmsDaoDatabase
import com.test.movieapplication.database.model.ResultDatabaseModel
import javax.inject.Inject

class FilmsRealizationDatabase (private val filmsDaoDatabase: FilmsDaoDatabase) : FilmsRepositoryDatabase {
    override fun insertToFavoriteTable(resultDatabaseModel: ResultDatabaseModel) {
        filmsDaoDatabase.insertToFavoriteTable(resultDatabaseModel = resultDatabaseModel)
    }

    override fun deleteFromFavoriteTable(resultDatabaseModel: ResultDatabaseModel) {
        filmsDaoDatabase.deleteFromFavoriteTable(resultDatabaseModel = resultDatabaseModel)
    }

    override fun getAllPopular(): LiveData<List<ResultDatabaseModel>> {
        return filmsDaoDatabase.getAllPopular()
    }


}