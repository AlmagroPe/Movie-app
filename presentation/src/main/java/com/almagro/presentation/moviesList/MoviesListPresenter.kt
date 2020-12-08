package com.almagro.presentation.moviesList

import com.almagro.domain.usecase.FetchOnAirMoviesUseCase
import com.almagro.domain.usecase.FetchPopularMoviesUseCase
import com.almagro.domain.usecase.FetchTopRatedMoviesUseCase
import com.almagro.presentation.WithScope

class MoviesListPresenter(
    private val view: MoviesListView?,
    private val fetchPopularMoviesUseCase: FetchPopularMoviesUseCase,
    private val fetchOnAirMoviesUseCase: FetchOnAirMoviesUseCase,
    private val fetchTopRatedMoviesUseCase: FetchTopRatedMoviesUseCase,
    private val withScope: WithScope
) : WithScope by withScope {

    private var page = 1

    fun onViewCreated() {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        launchIOSafe(
            f = { fetchPopularMoviesUseCase(page) },
            success = {
                page++
                view?.loadMovies(it.movies)
            },
            error = {}
        )
    }

    fun fetchOnAirMovies() {
        launchIOSafe(
            f = { fetchOnAirMoviesUseCase(page) },
            success = {
                page++
                view?.loadMovies(it.movies)
            },
            error = {}
        )
    }

    fun fetchTopRatedMovies() {
        launchIOSafe(
            f = { fetchTopRatedMoviesUseCase(page) },
            success = {
                page++
                view?.loadMovies(it.movies)
            },
            error = {}
        )
    }

    fun onClickMovie(movieId: Int) {
        view?.navigateToMovieDetail(movieId)
    }
}