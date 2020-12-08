package com.almagro.movieapp.di.moviesList

import com.almagro.domain.usecase.FetchOnAirMoviesUseCase
import com.almagro.domain.usecase.FetchPopularMoviesUseCase
import com.almagro.domain.usecase.FetchTopRatedMoviesUseCase
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
        fetchPopularMoviesUseCase: FetchPopularMoviesUseCase,
        fetchOnAirMoviesUseCase: FetchOnAirMoviesUseCase,
        fetchTopRatedMoviesUseCase: FetchTopRatedMoviesUseCase,
        withScope: WithScope
    ) = MoviesListPresenter(
        view = view,
        fetchPopularMoviesUseCase = fetchPopularMoviesUseCase,
        fetchOnAirMoviesUseCase = fetchOnAirMoviesUseCase,
        fetchTopRatedMoviesUseCase = fetchTopRatedMoviesUseCase,
        withScope = withScope
    )
}