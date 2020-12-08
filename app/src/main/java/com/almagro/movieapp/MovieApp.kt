package com.almagro.movieapp

import android.app.Application
import com.almagro.movieapp.di.AppComponent
import com.almagro.movieapp.di.AppModule
import com.almagro.movieapp.di.DaggerAppComponent

class MovieApp: Application() {

    val appComponent: AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
}