package com.rodrigolmti.coinzilla.util.extensions

import android.view.View
import java.text.NumberFormat
import java.util.*

/**
 * Created by rodrigolmti on 19/11/17.
 */

//region View Extensions
fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
//endregion

//region String Extensions
fun Double.formatCurrencyUSD(): String {
    return NumberFormat.getNumberInstance(Locale("pt", "BR")).format(this)
}

fun Double.formatCurrencyBRL(): String {
    return NumberFormat.getNumberInstance(Locale.US).format(this)
}
//endregion