package com.almagro.movieapp

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String?) {
    Glide.with(context)
            //TODO:move the path
        .load("https://image.tmdb.org/t/p/w500" + url)
        .into(this)
}