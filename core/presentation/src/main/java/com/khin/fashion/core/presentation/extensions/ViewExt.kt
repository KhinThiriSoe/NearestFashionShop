package com.khin.fashion.core.presentation.extensions

import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.goneUnless(visible: Boolean) = if (visible) visible() else gone()

fun View.invisibleUnless(visible: Boolean) = if (visible) visible() else invisible()