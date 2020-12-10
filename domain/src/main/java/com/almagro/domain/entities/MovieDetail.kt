package com.almagro.domain.entities

data class MovieDetail(
    val overview: String,
    override val id: Int,
    override val originalTitle: String,
    override val originalLanguage: String,
    override val title: String,
    override val backdropPath: String,
    override val popularity: Float,
    override val voteCount: Int,
    override val video: Boolean,
    override val voteAverage: Float,
    override val posterPath: String,
    override val adult: Boolean,
    override val releaseDate: String
) : Movie(
    id,
    originalTitle,
    originalLanguage,
    title,
    backdropPath,
    popularity,
    voteCount,
    video,
    voteAverage,
    posterPath,
    adult,
    releaseDate
) {
    companion object {
        fun empty() =
            MovieDetail(
                "",
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
}