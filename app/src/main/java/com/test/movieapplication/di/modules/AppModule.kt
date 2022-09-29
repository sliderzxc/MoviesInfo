package com.test.movieapplication.di.modules

import com.test.movieapplication.network.api.FilmApi
import com.test.movieapplication.network.repository.FilmsRepository
import com.test.movieapplication.paging.repository.FilmsRepositoryPaging
import com.test.movieapplication.utils.other.MainConstants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideFilmsRepositoryPaging(
        filmsRepository: FilmsRepository
    ) : FilmsRepositoryPaging {
        return FilmsRepositoryPaging(filmsRepository = filmsRepository)
    }

    @Provides
    @Singleton
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
    @Singleton
    fun provideFilmsRepository(
        filmsApi: FilmApi
    ) : FilmsRepository {
        return FilmsRepository(filmsApi = filmsApi)
    }

}