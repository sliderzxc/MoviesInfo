package com.test.movieapplication.di.modules

import com.test.movieapplication.paging.FilmsRepositoryPaging
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


}