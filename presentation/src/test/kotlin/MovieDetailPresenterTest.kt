package com.almagro.presentation.moviesList

import com.almagro.domain.usecase.FetchMovieDetailUseCase
import com.almagro.presentation.WithScope
import com.almagro.presentation.movieDetail.MovieDetailPresenter
import com.almagro.presentation.movieDetail.MovieDetailView
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.coroutines.CoroutineContext

@RunWith(MockitoJUnitRunner::class)
class MovieDetailPresenterTest {

    private lateinit var presenter: MovieDetailPresenter

    @Mock
    private lateinit var view: MovieDetailView

    @Mock
    private lateinit var fetchMovieDetailUseCase: FetchMovieDetailUseCase

    private val withScope = object : WithScope {
        override val coroutineContext: CoroutineContext
            get() = TestCoroutineDispatcher()
        override val io: CoroutineContext
            get() = TestCoroutineDispatcher()
    }

    @Before
    fun setUp() {
        presenter = MovieDetailPresenter(
            view = view,
            fetchMovieDetailUseCase = fetchMovieDetailUseCase,
            withScope = withScope
        )
    }
}