package com.test.movieapplication.di

import android.content.Context
import com.test.movieapplication.network.api.FilmApi
import com.test.movieapplication.paging.FilmsRepositoryPaging
import com.test.movieapplication.repository.FilmsRepository
import com.test.movieapplication.screens.fragment.main.MainViewModelFactory
import com.test.movieapplication.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext() : Context {
        return context
    }

    @Provides
    fun provideFilmsRepositoryPaging(
        filmsRepository: FilmsRepository
    ) : FilmsRepositoryPaging {
        return FilmsRepositoryPaging(filmsRepository = filmsRepository)
    }

    @Provides
    fun provideFilmApi(): FilmApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: FilmApi by lazy {
            retrofit.create(FilmApi::class.java)
        }
        return api
    }

    @Provides
    fun provideFilmsRepository(
        filmsApi: FilmApi
    ) : FilmsRepository {
        return FilmsRepository(filmsApi = filmsApi)
    }

    @Provides
    fun provideViewModelFactory(
        filmsRepositoryPaging: FilmsRepositoryPaging
    ) : MainViewModelFactory {
        return MainViewModelFactory(
            filmsRepositoryPaging = filmsRepositoryPaging
        )
    }

}