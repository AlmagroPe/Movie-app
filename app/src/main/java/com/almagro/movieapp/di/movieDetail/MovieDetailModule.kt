package com.almagro.movieapp.di.movieDetail

import com.almagro.movieapp.di.AsynchronyModule
import com.almagro.presentation.WithScope
import com.almagro.presentation.movieDetail.MovieDetailPresenter
import com.almagro.presentation.movieDetail.MovieDetailView
import dagger.Module
import dagger.Provides

@Module(includes = [AsynchronyModule::class])
class MovieDetailModule(private val view: MovieDetailView) {

    @Provides
    fun providePresenter(
        withScope: WithScope
    ) = MovieDetailPresenter(
        view,
        withScope
    )
}