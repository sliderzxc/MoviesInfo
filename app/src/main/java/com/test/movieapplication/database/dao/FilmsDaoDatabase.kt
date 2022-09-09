package com.test.movieapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.movieapplication.database.model.ResultDatabaseModel

@Entity
@Dao
interface FilmsDaoDatabase {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToFavoriteTable(resultDatabaseModel: ResultDatabaseModel)

    @Delete
    fun deleteFromFavoriteTable(resultDatabaseModel: ResultDatabaseModel)

    @Query("SELECT * FROM favorite_table")
    fun getAllPopular() : LiveData<List<ResultDatabaseModel>>
}