package com.almagro.data.apiDataSource

import arrow.core.Either
import com.almagro.data.entities.Failure
import com.almagro.data.entities.toDomain
import com.almagro.domain.entities.Movies
import com.almagro.movieapp.unWrap
import javax.inject.Inject

class MovieApiClientImpl
@Inject constructor(
    private val dealsRetrofitClient: MovieRetrofitClient
): MovieApiClient {

    override suspend fun fetchPopularMovies(): Either<Failure, Movies> =
        dealsRetrofitClient.api.fetchPopularMovies().unWrap().map { it.toDomain() }

    override suspend fun fetchOnAirMovies(): Either<Failure, Movies> =
        dealsRetrofitClient.api.fetchOnAirMovies().unWrap().map { it.toDomain() }

    override suspend fun fetchTopRatedMovies(): Either<Failure, Movies> =
        dealsRetrofitClient.api.fetchTopRatedMovies().unWrap().map { it.toDomain() }
}