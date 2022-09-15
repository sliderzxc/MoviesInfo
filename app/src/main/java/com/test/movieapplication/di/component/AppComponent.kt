package com.test.movieapplication.di.component

import com.test.movieapplication.di.modules.AppModule
import com.test.movieapplication.di.modules.DatabaseModule
import com.test.movieapplication.di.modules.ViewModelModule
import com.test.movieapplication.screens.activity.main.MainActivity
import com.test.movieapplication.screens.fragment.details.DetailsFragment
import com.test.movieapplication.screens.fragment.favorite.FavoriteFragment
import com.test.movieapplication.screens.fragment.image.ImageFragment
import com.test.movieapplication.screens.fragment.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(favoriteFragment: FavoriteFragment)
    fun inject(imageFragment: ImageFragment)
}