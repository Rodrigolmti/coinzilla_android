package com.rodrigolmti.coinzilla.util.extensions

import android.view.View
import java.text.DecimalFormat

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
fun String.formatCurrencyUSD() : String {
    val decimalFormat = DecimalFormat("$#.00")
    return decimalFormat.format(this.toFloat())
}

fun String.formatCurrencyBRL() : String {
    val decimalFormat = DecimalFormat("R$#.00")
    return decimalFormat.format(this.toFloat())
}
//endregion