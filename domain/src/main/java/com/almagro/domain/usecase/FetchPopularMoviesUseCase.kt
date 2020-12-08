package com.almagro.domain.usecase

import com.almagro.domain.gateway.MovieGateway
import javax.inject.Inject

class FetchPopularMoviesUseCase
@Inject constructor(
    private val movieGateway: MovieGateway
) {
    suspend operator fun invoke(page: Int) =
        movieGateway.fetchPopularMovies(page)
}