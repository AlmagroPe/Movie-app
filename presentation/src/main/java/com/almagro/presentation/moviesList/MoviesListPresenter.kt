package com.almagro.presentation.moviesList

import com.almagro.presentation.WithScope

class MoviesListPresenter(
    private val moviesListView: MoviesListView,
    private val withScope: WithScope
) : WithScope by withScope {
}