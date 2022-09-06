package com.test.movieapplication.di

import com.test.movieapplication.screens.activity.MainActivity
import com.test.movieapplication.screens.fragment.main.MainFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
}