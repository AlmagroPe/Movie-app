package com.almagro.movieapp.di.moviesList

import com.almagro.movieapp.di.AsynchronyModule
import com.almagro.presentation.WithScope
import com.almagro.presentation.moviesList.MoviesListPresenter
import com.almagro.presentation.moviesList.MoviesListView
import dagger.Module
import dagger.Provides

@Module(includes = [AsynchronyModule::class])
class MoviesListModule(private val view: MoviesListView) {

    @Provides
    fun providePresenter(
        withScope: WithScope
    ) = MoviesListPresenter(
        view,
        withScope
    )
}