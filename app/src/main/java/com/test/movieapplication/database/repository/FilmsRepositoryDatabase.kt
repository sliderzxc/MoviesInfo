package com.test.movieapplication.database.repository

import androidx.lifecycle.LiveData
import com.test.movieapplication.database.model.ResultDatabaseModel

interface FilmsRepositoryDatabase {

    fun insertToFavoriteTable(resultDatabaseModel: ResultDatabaseModel)

    fun deleteFromFavoriteTable(resultDatabaseModel: ResultDatabaseModel)

    fun getAllPopular() : LiveData<List<ResultDatabaseModel>>

}