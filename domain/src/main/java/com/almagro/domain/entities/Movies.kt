package com.almagro.domain.entities

data class Movies(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val movies: List<Movie>
)