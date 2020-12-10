package com.almagro.presentation.moviesList

import com.almagro.domain.entities.Movie

interface MoviesListView {

    fun loadMovies(moviesList: List<Movie>)

    fun navigateToMovieDetail(movieId: Int)

    fun showError(action: () -> Unit)

    fun clearMovieList()

    fun showLoading()

    fun hideLoading()

    fun showErrorView()
}