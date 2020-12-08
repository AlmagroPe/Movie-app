package com.almagro.domain.gateway

import arrow.core.Either
import com.almagro.domain.entities.DomainError
import com.almagro.domain.entities.Movies

interface MoviesGateway {

    suspend fun fetchPopularMovies(): Either<DomainError, Movies>

    suspend fun fetchOnAirMovies(): Either<DomainError, Movies>

    suspend fun fetchTopRatedMovies(): Either<DomainError, Movies>
}