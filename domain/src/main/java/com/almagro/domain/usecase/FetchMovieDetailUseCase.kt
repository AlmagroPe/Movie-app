package com.almagro.domain.usecase

import com.almagro.domain.gateway.MovieGateway
import javax.inject.Inject

class FetchMovieDetailUseCase
@Inject constructor(
    private val movieGateway: MovieGateway
) {
    suspend operator fun invoke(movieId: Int) =
        movieGateway.fetchMovieDetail(movieId)
}