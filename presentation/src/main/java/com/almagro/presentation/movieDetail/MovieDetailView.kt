package com.almagro.presentation.movieDetail

import com.almagro.domain.entities.MovieDetail

interface MovieDetailView {

    fun setUpView(movieDetail: MovieDetail)
}