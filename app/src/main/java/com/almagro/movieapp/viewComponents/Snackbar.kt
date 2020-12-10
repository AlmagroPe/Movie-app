package com.almagro.movieapp.viewComponents

import android.view.View
import androidx.core.content.ContextCompat
import com.almagro.movieapp.R
import com.google.android.material.snackbar.Snackbar

fun View.snackBar(
    message: Int = 0,
    length: Int = Snackbar.LENGTH_INDEFINITE,
    actionText: String = "",
    backgroundColor: Int = 0,
    backgroundTextColor: Int = 0,
    action: () -> Unit = {}
) {
    val snackBar = Snackbar.make(this, message, length)
    snackBar.setAction(actionText) {
        action()
        snackBar.dismiss()
    }
    snackBar.setActionTextColor(ContextCompat.getColor(context, backgroundTextColor))
    snackBar.view.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
    snackBar.show()
}

fun View.showSnackBarError(message: Int, action: () -> Unit) {
    this.snackBar(
        message = message,
        actionText = resources.getString(R.string.retry),
        backgroundColor = R.color.red,
        backgroundTextColor = R.color.white,
        action = action
    )
}