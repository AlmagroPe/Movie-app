package com.almagro.presentation.moviesList

import arrow.core.left
import arrow.core.right
import com.almagro.domain.entities.DomainError
import com.almagro.domain.entities.MovieDetail
import com.almagro.domain.entities.Movies
import com.almagro.domain.usecase.FetchOnAirMoviesUseCase
import com.almagro.domain.usecase.FetchPopularMoviesUseCase
import com.almagro.domain.usecase.FetchTopRatedMoviesUseCase
import com.almagro.presentation.WithScope
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.coroutines.CoroutineContext
import kotlin.test.assertTrue

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

    @Test
    fun `should fetch popular movies when view is created`() {
        //When
        presenter.onViewCreated()

        //Then
        runBlocking {
            verify(fetchPopularMoviesUseCase, times(1))
                .invoke(any())
        }
    }

    @Test
    fun `should load movies when popular movies call response is successful`() {
        //Given
        runBlocking {
            Mockito.doAnswer {
                Movies.empty().right()
            }.whenever(fetchPopularMoviesUseCase).invoke(any())
        }

        //When
        presenter.fetchPopularMovies()

        //Then
        Mockito.verify(view).loadMovies(any())
    }

    @Test
    fun `should show error when popular movies call response is unsuccesful`() {
        //Given
        runBlocking {
            Mockito.doAnswer {
                DomainError.ServerError.left()
            }.whenever(fetchPopularMoviesUseCase).invoke(any())
        }

        //When
        presenter.fetchPopularMovies()

        //Then
        Mockito.verify(view).showError(any())
    }

    @Test
    fun `should load movies when on air movies call response is successful`() {
        //Given
        runBlocking {
            Mockito.doAnswer {
                Movies.empty().right()
            }.whenever(fetchOnAirMoviesUseCase).invoke(any())
        }

        //When
        presenter.fetchOnAirMovies()

        //Then
        Mockito.verify(view).loadMovies(any())
    }

    @Test
    fun `should show error when on air movies call response is unsuccesful`() {
        //Given
        runBlocking {
            Mockito.doAnswer {
                DomainError.ServerError.left()
            }.whenever(fetchOnAirMoviesUseCase).invoke(any())
        }

        //When
        presenter.fetchOnAirMovies()

        //Then
        Mockito.verify(view).showError(any())
    }

    @Test
    fun `should load movies when top rated movies call response is successful`() {
        //Given
        runBlocking {
            Mockito.doAnswer {
                Movies.empty().right()
            }.whenever(fetchTopRatedMoviesUseCase).invoke(any())
        }

        //When
        presenter.fetchTopRatedMovies()

        //Then
        Mockito.verify(view).loadMovies(any())
    }

    @Test
    fun `should show error when top rated movies call response is unsuccesful`() {
        //Given
        runBlocking {
            Mockito.doAnswer {
                DomainError.ServerError.left()
            }.whenever(fetchTopRatedMoviesUseCase).invoke(any())
        }

        //When
        presenter.fetchTopRatedMovies()

        //Then
        Mockito.verify(view).showError(any())
    }

    @Test
    fun `should clear movie list when change type of movies`() {
        //When
        presenter.resetCurrentValues()

        //Then
        runBlocking {
            verify(view, times(1)).clearMovieList()
        }
    }
}