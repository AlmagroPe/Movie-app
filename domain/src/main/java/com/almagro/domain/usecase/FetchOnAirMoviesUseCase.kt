package com.almagro.domain.usecase

import com.almagro.domain.gateway.MoviesGateway
import javax.inject.Inject

class FetchOnAirMoviesUseCase
@Inject constructor(
    private val moviesGateway: MoviesGateway
) {
    suspend operator fun invoke() = moviesGateway.fetchOnAirMovies()
}