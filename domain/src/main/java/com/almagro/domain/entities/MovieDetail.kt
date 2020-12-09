package com.almagro.domain.entities

data class MovieDetail(
    val id: Int,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String,
    val popularity: Float,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Float,
    val posterPath: String,
    val adult: Boolean,
    val releaseDate: String,
    val overview: String
) {
    companion object {
        fun empty() =
            MovieDetail(
                0,
                "",
                "",
                "",
                "",
                0F,
                0,
                false,
                0F,
                "",
                false,
                "",
                ""
            )
    }
}