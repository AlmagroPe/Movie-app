package com.almagro.movieapp.di

import com.almagro.movieapp.di.movieDetail.MovieDetailComponent
import com.almagro.movieapp.di.movieDetail.MovieDetailModule
import com.almagro.movieapp.di.moviesList.MoviesListComponent
import com.almagro.movieapp.di.moviesList.MoviesListModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        GatewayModule::class
    ]
)
interface AppComponent {

    fun provideMoviesListComponent(dealsListModule: MoviesListModule): MoviesListComponent

    fun provideMovieDetailComponent(DealDetailModule: MovieDetailModule): MovieDetailComponent
}