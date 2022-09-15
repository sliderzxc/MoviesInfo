package com.test.movieapplication.database.repository

import androidx.lifecycle.LiveData
import com.test.movieapplication.database.model.ResultDatabaseModel

interface FilmsRepositoryDatabase {

    suspend fun insertToFavoriteTable(resultDatabaseModel: ResultDatabaseModel)

    suspend fun deleteFromFavoriteTable(resultDatabaseModel: ResultDatabaseModel)

    fun getAllFavoriteFilms() : LiveData<List<ResultDatabaseModel>>

    suspend fun isExistFilmInDatabase(id: Int) : Int

}