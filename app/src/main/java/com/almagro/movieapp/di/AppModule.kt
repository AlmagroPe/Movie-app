package com.almagro.movieapp.di

import android.app.Application
import android.content.Context
import com.almagro.movieapp.MovieApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(movieApp: MovieApp)  {

    private val application: Application

    init {
        this.application = movieApp
    }

    @Provides
    @Singleton
    fun provideContext(): Context = application
}