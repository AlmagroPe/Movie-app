package com.almagro.movieapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var currentPosition = 0
        private set

    open fun onBind(position: Int) {
        currentPosition = position
    }
}