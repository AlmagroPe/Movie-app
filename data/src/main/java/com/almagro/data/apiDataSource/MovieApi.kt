package com.almagro.data.apiDataSource

import com.almagro.data.entities.MovieDetailDto
import com.almagro.data.entities.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int): Response<MoviesDto>

    @GET("movie/now_playing")
    suspend fun fetchOnAirMovies(@Query("page") page: Int): Response<MoviesDto>

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(@Query("page") page: Int): Response<MoviesDto>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetail(@Path("movie_id") movieId: Int): Response<MovieDetailDto>
}