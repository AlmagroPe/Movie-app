package com.almagro.presentation.movieDetail

import com.almagro.domain.entities.MovieDetail

interface MovieDetailView {

    fun setUpView(movieDetail: MovieDetail)

    fun showError(action: () -> Unit)

    fun showLoading()

    fun hideLoading()

    fun showErrorView()
}