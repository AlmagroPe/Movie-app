package com.almagro.data.apiDataSource

import com.almagro.data.entities.MoviesDto
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("/movie/popular")
    suspend fun fetchPopularMovies(): Response<MoviesDto>

//    @GET("/tv/airing_today")
//    suspend fun fetchAiringMovies(): Response<MoviesDto>

    @GET("/movie/now_playing")
    suspend fun fetchOnAirMovies(): Response<MoviesDto>

    @GET("/movie/top_rated")
    suspend fun fetchTopRatedMovies(): Response<MoviesDto>
}