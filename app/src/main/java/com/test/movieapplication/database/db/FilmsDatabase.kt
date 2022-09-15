package com.test.movieapplication.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.movieapplication.database.dao.FilmsDaoDatabase
import com.test.movieapplication.database.model.ResultDatabaseModel


@Database(entities = [ResultDatabaseModel::class], version = 3, exportSchema = false)
abstract class FilmsDatabase : RoomDatabase(){
    abstract fun getToDoDao() : FilmsDaoDatabase

    companion object {
        private var database: FilmsDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : FilmsDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, FilmsDatabase::class.java, "main_database")
                    .fallbackToDestructiveMigration()
                    .build()
                database as FilmsDatabase
            } else {
                database as FilmsDatabase
            }
        }

    }
}