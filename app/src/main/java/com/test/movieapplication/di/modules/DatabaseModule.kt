package com.test.movieapplication.di.modules

import android.app.Application
import android.content.Context
import com.test.movieapplication.database.dao.FilmsDaoDatabase
import com.test.movieapplication.database.db.FilmsDatabase
import com.test.movieapplication.database.repository.FilmsRealizationDatabase
import com.test.movieapplication.database.repository.FilmsRepositoryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideContext() : Context {
        return application
    }

    @Singleton
    @Provides
    fun provideFilmsDaoDatabase(context: Context) : FilmsDaoDatabase {
        return FilmsDatabase.getInstance(context = context).getToDoDao()
    }

    @Singleton
    @Provides
    fun provideFilmsRepositoryDatabase(filmsDaoDatabase: FilmsDaoDatabase) : FilmsRepositoryDatabase {
        return FilmsRealizationDatabase(filmsDaoDatabase = filmsDaoDatabase)
    }


}