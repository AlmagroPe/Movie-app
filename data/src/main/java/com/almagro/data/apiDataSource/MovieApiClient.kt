package com.almagro.data.apiDataSource

import arrow.core.Either
import com.almagro.data.entities.Failure
import com.almagro.domain.entities.Movies

interface MovieApiClient {

    suspend fun fetchPopularMovies(): Either<Failure, Movies>

//    suspend fun fetchAiringMovies(): Either<Failure, Movies>

    suspend fun fetchOnAirMovies(): Either<Failure, Movies>

    suspend fun fetchTopRatedMovies(): Either<Failure, Movies>
}