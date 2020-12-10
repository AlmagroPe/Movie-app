package com.almagro.presentation.moviesList

import com.almagro.domain.entities.FilterType
import com.almagro.domain.entities.OnAirMovies
import com.almagro.domain.entities.PopularMovies
import com.almagro.domain.entities.TopRatedMovies
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
    private var filterType: FilterType = PopularMovies

    fun onViewCreated() {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        filterType = PopularMovies
        view?.showLoading()
        launchIOSafe(
            f = { fetchPopularMoviesUseCase(page) },
            success = {
                page++
                view?.loadMovies(it.movies)
                view?.hideLoading()
            },
            error = {
                view?.showErrorView()
                view?.showError { fetchPopularMovies() }
            }
        )
    }

    fun fetchOnAirMovies() {
        filterType = OnAirMovies
        view?.showLoading()
        launchIOSafe(
            f = { fetchOnAirMoviesUseCase(page) },
            success = {
                page++
                view?.loadMovies(it.movies)
                view?.hideLoading()
            },
            error = {
                view?.showErrorView()
                view?.showError { fetchOnAirMovies() }
            }
        )
    }

    fun fetchTopRatedMovies() {
        filterType = TopRatedMovies
            view?.showLoading()
        launchIOSafe(
            f = { fetchTopRatedMoviesUseCase(page) },
            success = {
                page++
                view?.loadMovies(it.movies)
                view?.hideLoading()
            },
            error = {
                view?.showErrorView()
                view?.showError { fetchTopRatedMovies() }
            }
        )
    }

    fun onClickMovie(movieId: Int) {
        view?.navigateToMovieDetail(movieId)
    }

    fun resetCurrentValues() {
        page = 1
        view?.clearMovieList()
    }

    fun updateMoviesByType() {
        when(filterType) {
            PopularMovies -> fetchPopularMovies()
            OnAirMovies -> fetchOnAirMovies()
            TopRatedMovies -> fetchTopRatedMovies()
        }
    }
}