package com.test.movieapplication.app

import android.app.Application
import com.test.movieapplication.di.component.AppComponent
import com.test.movieapplication.di.component.DaggerAppComponent
import com.test.movieapplication.di.modules.AppModule
import com.test.movieapplication.di.modules.DatabaseModule
import com.test.movieapplication.di.modules.ViewModelModule

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule())
            .viewModelModule(ViewModelModule())
            .databaseModule(DatabaseModule(this))
            .build()
    }

}