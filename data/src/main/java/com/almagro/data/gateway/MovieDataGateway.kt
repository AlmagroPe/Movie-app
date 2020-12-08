package com.almagro.data.gateway

import arrow.core.Either
import com.almagro.data.apiDataSource.MovieApiClient
import com.almagro.domain.entities.DomainError
import com.almagro.domain.entities.Movies
import com.almagro.domain.gateway.MovieGateway
import javax.inject.Inject

class MovieDataGateway
@Inject constructor(
    private val movieApiClient: MovieApiClient
) : MovieGateway {

    override suspend fun fetchPopularMovies(): Either<DomainError, Movies> =
        movieApiClient.fetchPopularMovies()

    override suspend fun fetchOnAirMovies(): Either<DomainError, Movies> =
        movieApiClient.fetchOnAirMovies()

    override suspend fun fetchTopRatedMovies(): Either<DomainError, Movies> =
        movieApiClient.fetchTopRatedMovies()

}