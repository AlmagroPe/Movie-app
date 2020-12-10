package com.almagro.domain.entities

open class Movie(
    open val id: Int,
    open val originalTitle: String,
    open val originalLanguage: String,
    open val title: String,
    open val backdropPath: String,
    open val popularity: Float,
    open val voteCount: Int,
    open val video: Boolean,
    open val voteAverage: Float,
    open val posterPath: String,
    open val adult: Boolean,
    open val releaseDate: String
) {
    companion object {
        fun empty() =
            Movie(
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
                ""
            )
    }

    val fullPosterPath
        get() = "https://image.tmdb.org/t/p/w500$posterPath"

    val fullBackdropPath
        get() = "https://image.tmdb.org/t/p/w500$backdropPath"
}