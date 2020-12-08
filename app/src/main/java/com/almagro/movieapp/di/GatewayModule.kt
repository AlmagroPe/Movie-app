package com.almagro.movieapp.di

import com.almagro.data.apiDataSource.MovieApiClient
import com.almagro.data.apiDataSource.MovieApiClientImpl
import com.almagro.data.apiDataSource.MovieRetrofitClient
import com.almagro.data.gateway.MovieDataGateway
import com.almagro.domain.gateway.MovieGateway
import dagger.Module
import dagger.Provides

@Module
class GatewayModule {

    @Provides
    fun provideDealGateway(movieApiClient: MovieApiClient): MovieGateway =
        MovieDataGateway(movieApiClient)

    @Provides
    fun provideDealsApiClient(movieRetrofitClient: MovieRetrofitClient): MovieApiClient =
        MovieApiClientImpl(movieRetrofitClient)
}