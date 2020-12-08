package com.almagro.presentation.movieDetail

import com.almagro.presentation.WithScope

class MovieDetailPresenter(
    private val movieDetailView: MovieDetailView,
    withScope: WithScope
) : WithScope by withScope {
}