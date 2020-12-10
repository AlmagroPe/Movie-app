package com.almagro.data.apiDataSource

import arrow.core.Either
import com.almagro.data.entities.Failure
import com.almagro.domain.entities.DomainError
import com.almagro.domain.entities.MovieDetail
import com.almagro.domain.entities.Movies

interface MovieApiClient {

    suspend fun fetchPopularMovies(page: Int): Either<DomainError, Movies>
    
    suspend fun fetchOnAirMovies(page: Int): Either<DomainError, Movies>

    suspend fun fetchTopRatedMovies(page: Int): Either<DomainError, Movies>

    suspend fun fetchMovieDetail(movieId: Int): Either<DomainError, MovieDetail>
}