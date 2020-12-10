package com.almagro.movieapp.di.movieDetail

import com.almagro.movieapp.activities.movieDetail.MovieDetailActivity
import com.almagro.movieapp.di.moviesList.MoviesListScope
import dagger.Subcomponent

@MovieDetailScope
@Subcomponent(
    modules = [MovieDetailModule::class]
)
interface MovieDetailComponent {
    fun inject(activity: MovieDetailActivity)
}