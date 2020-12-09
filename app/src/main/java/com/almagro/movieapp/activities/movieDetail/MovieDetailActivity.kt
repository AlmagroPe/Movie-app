package com.almagro.movieapp.activities.movieDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.almagro.domain.entities.MovieDetail
import com.almagro.movieapp.MovieApp
import com.almagro.movieapp.R
import com.almagro.movieapp.di.movieDetail.MovieDetailModule
import com.almagro.movieapp.loadUrl
import com.almagro.presentation.movieDetail.MovieDetailPresenter
import com.almagro.presentation.movieDetail.MovieDetailView
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity(), MovieDetailView {

    companion object {
        private const val MOVIE_ID = "movieId"

        fun create(context: Context, movieId: Int) =
            Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
    }

    @Inject
    lateinit var presenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setUpComponent()
        presenter.onCreated(intent?.extras?.getInt(MOVIE_ID) ?: 0)
    }

    override fun onDestroy() {
        presenter.cancel()
        super.onDestroy()
    }

    override fun setUpView(movieDetail: MovieDetail) {
        ivMovieDetail.loadUrl(movieDetail.backdropPath)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setUpComponent() {
        (application as MovieApp)
            .appComponent
            .provideMovieDetailComponent(MovieDetailModule(this))
            .inject(this)
    }
}