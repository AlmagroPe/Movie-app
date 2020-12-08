package com.almagro.data.entities

import com.almagro.domain.entities.Movies
import com.google.gson.annotations.SerializedName

data class MoviesDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val movies: List<MovieDto>
)

fun MoviesDto.toDomain() =
    Movies(
        page = page,
        totalResults = totalResults,
        totalPages = totalPages,
        movies = movies.map { it.toDomain() }
    )
