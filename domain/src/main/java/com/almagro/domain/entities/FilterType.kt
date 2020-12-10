package com.almagro.domain.entities

sealed class FilterType(value: Int)
object PopularMovies: FilterType(0)
object OnAirMovies: FilterType(1)
object TopRatedMovies: FilterType(2)