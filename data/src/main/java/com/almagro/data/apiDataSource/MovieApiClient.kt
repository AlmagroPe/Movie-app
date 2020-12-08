package com.almagro.data.apiDataSource

import arrow.core.Either
import com.almagro.data.entities.Failure
import com.almagro.domain.entities.DomainError
import com.almagro.domain.entities.Movies

interface MovieApiClient {

    suspend fun fetchPopularMovies(): Either<DomainError, Movies>

//    suspend fun fetchAiringMovies(): Either<Failure, Movies>

    suspend fun fetchOnAirMovies(): Either<DomainError, Movies>

    suspend fun fetchTopRatedMovies(): Either<DomainError, Movies>
}