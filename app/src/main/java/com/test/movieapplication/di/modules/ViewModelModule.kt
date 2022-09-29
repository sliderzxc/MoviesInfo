package com.test.movieapplication.di.modules

import com.test.movieapplication.database.repository.FilmsRealizationDatabase
import com.test.movieapplication.paging.repository.FilmsRepositoryPaging
import com.test.movieapplication.screens.fragment.details.DetailsViewModelFactory
import com.test.movieapplication.screens.fragment.favorite.FavoriteViewModelFactory
import com.test.movieapplication.screens.fragment.main.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideMainViewModelFactory(
        filmsRepositoryPaging: FilmsRepositoryPaging
    ) : MainViewModelFactory {
        return MainViewModelFactory(
            filmsRepositoryPaging = filmsRepositoryPaging
        )
    }

    @Provides
    fun provideDetailsViewModelFactory(
        filmsRealizationDatabase: FilmsRealizationDatabase
    ) : DetailsViewModelFactory {
        return DetailsViewModelFactory(filmsRepositoryDatabase = filmsRealizationDatabase)
    }

    @Provides
    fun provideFavoriteViewModelFactory(
        filmsRealizationDatabase: FilmsRealizationDatabase
    ) : FavoriteViewModelFactory {
        return FavoriteViewModelFactory(filmsRepositoryDatabase = filmsRealizationDatabase)
    }

}