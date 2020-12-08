package com.almagro.movieapp

import android.app.Application
import com.almagro.movieapp.di.AppComponent
import com.almagro.movieapp.di.AppModule

class MovieApp: Application() {

    val appComponent: AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
}