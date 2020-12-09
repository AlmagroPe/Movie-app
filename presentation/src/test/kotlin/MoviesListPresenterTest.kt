package com.almagro.presentation.moviesList

import com.almagro.domain.usecase.FetchOnAirMoviesUseCase
import com.almagro.domain.usecase.FetchPopularMoviesUseCase
import com.almagro.domain.usecase.FetchTopRatedMoviesUseCase
import com.almagro.presentation.WithScope
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.coroutines.CoroutineContext

@RunWith(MockitoJUnitRunner::class)
class MoviesListPresenterTest {

    private lateinit var presenter: MoviesListPresenter

    @Mock
    private lateinit var view: MoviesListView

    @Mock
    private lateinit var fetchPopularMoviesUseCase: FetchPopularMoviesUseCase

    @Mock
    private lateinit var fetchOnAirMoviesUseCase: FetchOnAirMoviesUseCase

    @Mock
    private lateinit var fetchTopRatedMoviesUseCase: FetchTopRatedMoviesUseCase

    private val withScope = object : WithScope {
        override val coroutineContext: CoroutineContext
            get() = TestCoroutineDispatcher()
        override val io: CoroutineContext
            get() = TestCoroutineDispatcher()
    }

    @Before
    fun setUp() {
        presenter = MoviesListPresenter(
            view = view,
            fetchPopularMoviesUseCase = fetchPopularMoviesUseCase,
            fetchOnAirMoviesUseCase = fetchOnAirMoviesUseCase,
            fetchTopRatedMoviesUseCase = fetchTopRatedMoviesUseCase,
            withScope = withScope
        )
    }
}