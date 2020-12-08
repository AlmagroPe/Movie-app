package com.almagro.movieapp.activities.moviesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.almagro.domain.entities.Movie
import com.almagro.domain.entities.Movies
import com.almagro.movieapp.R
import com.almagro.movieapp.core.BaseViewHolder
import com.almagro.movieapp.loadUrl
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesListAdapter(
    private var itemClick: (Int) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_DEAL = 1

        var isLoaderVisible = false
    }

    private val moviesList: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            VIEW_TYPE_LOADING ->
                LoadingHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_loading, parent, false)
                )
            else ->
                ItemHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_movie, parent, false),
                    moviesList,
                    itemClick
                )
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        holder.onBind(position)

    override fun getItemCount(): Int = moviesList.size

    fun addItems(moviesList: List<Movie>) {
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = true
        moviesList.add(Movie.empty())
        notifyItemInserted(moviesList.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position: Int = moviesList.size - 1
        if (position >= 0) {
            moviesList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        moviesList.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int =
        if (isLoaderVisible && position == moviesList.size - 1) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_DEAL
        }

    class ItemHolder(
        itemView: View,
        val moviesList: List<Movie>,
        val itemClick: (Int) -> Unit
    ) : BaseViewHolder(itemView) {

        override fun onBind(position: Int) {
            super.onBind(position)
            val movie = moviesList[position]

            itemView.apply {
                ivMovie.loadUrl(movie.posterPath)
                tvNameMovie.text = movie.title
                tvValuePopularity.text = movie.popularity.toString()
                tvDateMovie.text = movie.releaseDate
                clRoot.setOnClickListener { itemClick(movie.id) }
            }
        }
    }

    class LoadingHolder(itemView: View) : BaseViewHolder(itemView)
}