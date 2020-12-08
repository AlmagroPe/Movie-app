package com.almagro.data.entities

import com.almagro.domain.entities.Movie
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("release_date")
    val releaseDate: String
)

fun MovieDto.toDomain() =
    Movie(
        id = id,
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        title = title,
        backdropPath = backdropPath,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage,
        posterPath = posterPath,
        adult = adult,
        releaseDate = releaseDate
    )
