package com.rodrigolmti.coinzilla.coinzilla.view.extensions

import android.view.View

/**
 * Created by rodrigolmti on 19/11/17.
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
