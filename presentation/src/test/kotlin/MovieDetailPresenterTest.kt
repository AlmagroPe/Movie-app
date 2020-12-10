package com.almagro.presentation.moviesList

import arrow.core.left
import arrow.core.right
import com.almagro.domain.entities.DomainError
import com.almagro.domain.entities.MovieDetail
import com.almagro.domain.usecase.FetchMovieDetailUseCase
import com.almagro.presentation.WithScope
import com.almagro.presentation.movieDetail.MovieDetailPresenter
import com.almagro.presentation.movieDetail.MovieDetailView
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
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

    @Test
    fun `should request movie detail when view is created`() {
        //When
        presenter.onCreated(any())

        //Then
        runBlocking {
            verify(fetchMovieDetailUseCase, com.nhaarman.mockitokotlin2.times(1))
                .invoke(any())
        }
    }

    @Test
    fun `should set up view when has the movie data`() {
        //Given
        runBlocking {
            doAnswer {
                MovieDetail.empty().right()
            }.whenever(fetchMovieDetailUseCase)(any())
        }

        //When
        presenter.onCreated(any())

        //Then
        verify(view).setUpView(any())
    }

    @Test
    fun `should show error when has not the movie data`() {
        //Given
        runBlocking {
            doAnswer {
                DomainError.ServerError.left()
            }.whenever(fetchMovieDetailUseCase)(any())
        }

        //When
        presenter.onCreated(any())

        //Then
        verify(view).showError(any())
    }
}