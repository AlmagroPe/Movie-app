package com.almagro.domain.entities

data class Movies(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val movies: List<Movie>
) {
    companion object {
        fun empty() =
            Movies(
                0,
                0,
                0,
                listOf()
            )
    }
}