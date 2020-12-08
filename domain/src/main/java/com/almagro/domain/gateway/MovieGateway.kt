package com.almagro.domain.gateway

import arrow.core.Either
import com.almagro.domain.entities.DomainError
import com.almagro.domain.entities.Movies

interface MovieGateway {

    suspend fun fetchPopularMovies(page: Int): Either<DomainError, Movies>

    suspend fun fetchOnAirMovies(page: Int): Either<DomainError, Movies>

    suspend fun fetchTopRatedMovies(page: Int): Either<DomainError, Movies>
}