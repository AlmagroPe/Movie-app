package com.almagro.movieapp.di

import com.almagro.presentation.WithScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

@Module
class AsynchronyModule(
    private val job: Job = Job()
) {

    @Provides
    fun provideWithScope(): WithScope =
        object : WithScope {
            override val coroutineContext: CoroutineContext = Dispatchers.Main + job
            override val io: CoroutineContext = Dispatchers.IO + job
        }
}
