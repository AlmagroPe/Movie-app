package com.almagro.data.apiDataSource

import arrow.core.Either
import com.almagro.data.entities.Failure
import com.almagro.data.entities.parseError
import com.almagro.data.entities.toDomain
import com.almagro.domain.entities.DomainError
import com.almagro.domain.entities.Movies
import com.almagro.movieapp.unWrap
import javax.inject.Inject

class MovieApiClientImpl
@Inject constructor(
    private val dealsRetrofitClient: MovieRetrofitClient
): MovieApiClient {

    override suspend fun fetchPopularMovies(page: Int): Either<DomainError, Movies> =
        dealsRetrofitClient.api.fetchPopularMovies(page).unWrap()
            .map { it.toDomain() }
            .mapLeft { it.parseError() }

    override suspend fun fetchOnAirMovies(page: Int): Either<DomainError, Movies> =
        dealsRetrofitClient.api.fetchOnAirMovies(page).unWrap()
            .map { it.toDomain() }
            .mapLeft { it.parseError() }

    override suspend fun fetchTopRatedMovies(page: Int): Either<DomainError, Movies> =
        dealsRetrofitClient.api.fetchTopRatedMovies(page).unWrap()
            .map { it.toDomain() }
            .mapLeft { it.parseError() }
}