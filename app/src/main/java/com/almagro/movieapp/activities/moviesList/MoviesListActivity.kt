package com.almagro.movieapp.activities.moviesList

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.almagro.domain.entities.Movie
import com.almagro.movieapp.MovieApp
import com.almagro.movieapp.R
import com.almagro.movieapp.activities.movieDetail.MovieDetailActivity
import com.almagro.movieapp.core.PaginationScrollListener
import com.almagro.movieapp.di.moviesList.MoviesListModule
import com.almagro.presentation.moviesList.MoviesListPresenter
import com.almagro.presentation.moviesList.MoviesListView
import kotlinx.android.synthetic.main.activity_movies_list.*
import javax.inject.Inject

class MoviesListActivity : AppCompatActivity(), MoviesListView {

    @Inject
    lateinit var presenter: MoviesListPresenter

    private var moviesListAdapter: MoviesListAdapter? = null
    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        setUpComponent()
        setUpRecyclerView()
        setUpListeners()
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        presenter.cancel()
        super.onDestroy()
    }

    override fun loadMovies(moviesList: List<Movie>) {
        swipeRefreshLayout.isRefreshing = false
        isLoading = false
        isLastPage = false
        moviesListAdapter?.removeLoading()
        moviesListAdapter?.addItems(moviesList)
    }

    override fun navigateToMovieDetail(movieId: Int) {
        startActivity(MovieDetailActivity.create(this, movieId))
    }

    private fun setUpComponent() {
        (application as MovieApp)
            .appComponent
            .provideMoviesListComponent(MoviesListModule(this))
            .inject(this)
    }

    private fun setUpRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this@MoviesListActivity, 2)
        moviesListAdapter = MoviesListAdapter { presenter.onClickMovie(it) }

        rvMoviesList.apply {
            layoutManager = gridLayoutManager
            adapter = moviesListAdapter

            addOnScrollListener(object : PaginationScrollListener(gridLayoutManager) {
                override fun loadMoreItems() {
                    moviesListAdapter?.addLoading()
                    isLoading = true
                    presenter.fetchPopularMovies()
                }

                override fun isLastPage(): Boolean = isLastPage

                override fun isLoading(): Boolean = isLoading
            })
        }
    }

    private fun setUpListeners() {
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            moviesListAdapter?.clear()
            presenter.fetchPopularMovies()
        }
    }
}