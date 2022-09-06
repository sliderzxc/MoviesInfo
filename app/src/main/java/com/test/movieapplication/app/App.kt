package com.test.movieapplication.app

import android.app.Application
import com.test.movieapplication.di.AppComponent
import com.test.movieapplication.di.AppModule
import com.test.movieapplication.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}