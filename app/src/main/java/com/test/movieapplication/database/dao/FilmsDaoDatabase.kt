package com.test.movieapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.movieapplication.database.model.ResultDatabaseModel

@Entity
@Dao
interface FilmsDaoDatabase {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToFavoriteTable(resultDatabaseModel: ResultDatabaseModel)

    @Delete
    suspend fun deleteFromFavoriteTable(resultDatabaseModel: ResultDatabaseModel)

    @Query("SELECT * FROM favorite_table")
    fun getAllPopular() : LiveData<List<ResultDatabaseModel>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_table WHERE id = :arg0 LIMIT 1)")
    suspend fun isExistFilmInDatabase(arg0: Int): Int
}