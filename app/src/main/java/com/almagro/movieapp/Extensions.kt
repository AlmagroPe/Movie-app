package com.almagro.movieapp

import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun String.formatDate(): String {
    val formatter = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()) //yyyy-MM-dd'T'HH:mm:ss
    val output = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return if (this.isNotEmpty()){
        output.format(formatter.parse(this) ?: Date())
    } else {
        ""
    }
}