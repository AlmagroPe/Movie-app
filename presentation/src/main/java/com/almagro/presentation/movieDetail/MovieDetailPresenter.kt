package com.almagro.presentation.movieDetail

import com.almagro.domain.usecase.FetchMovieDetailUseCase
import com.almagro.presentation.WithScope

class MovieDetailPresenter(
    private val view: MovieDetailView?,
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    withScope: WithScope
) : WithScope by withScope {

    fun onCreated(movieId: Int) {
        view?.showLoading()
        launchIOSafe(
            f = { fetchMovieDetailUseCase(movieId) },
            success = {
                view?.setUpView(it)
                view?.hideLoading()
            },
            error = {
                view?.showErrorView()
                view?.showError { onCreated(movieId) }
            }
        )
    }
}