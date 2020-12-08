package com.almagro.movieapp.di.moviesList

import com.almagro.movieapp.activities.moviesList.MoviesListActivity
import dagger.Subcomponent

@MoviesListScope
@Subcomponent(
    modules = [MoviesListModule::class]
)
interface MoviesListComponent {
    fun inject(activity: MoviesListActivity)
}