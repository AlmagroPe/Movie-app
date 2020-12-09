package com.almagro.data.gateway

import arrow.core.Either
import com.almagro.data.apiDataSource.MovieApiClient
import com.almagro.domain.entities.DomainError
import com.almagro.domain.entities.MovieDetail
import com.almagro.domain.entities.Movies
import com.almagro.domain.gateway.MovieGateway
import javax.inject.Inject

class MovieDataGateway
@Inject constructor(
    private val movieApiClient: MovieApiClient
) : MovieGateway {

    override suspend fun fetchPopularMovies(page: Int): Either<DomainError, Movies> =
        movieApiClient.fetchPopularMovies(page)

    override suspend fun fetchOnAirMovies(page: Int): Either<DomainError, Movies> =
        movieApiClient.fetchOnAirMovies(page)

    override suspend fun fetchTopRatedMovies(page: Int): Either<DomainError, Movies> =
        movieApiClient.fetchTopRatedMovies(page)

    override suspend fun fetchMovieDetail(movieId: Int): Either<DomainError, MovieDetail> =
        movieApiClient.fetchMovieDetail(movieId)

}