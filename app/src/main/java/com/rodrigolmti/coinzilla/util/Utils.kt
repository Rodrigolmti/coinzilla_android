package com.rodrigolmti.coinzilla.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import androidx.core.content.ContextCompat
import com.rodrigolmti.coinzilla.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/* UI Functions */

fun showSnackBar(view: View, context: Context, message: String) {
    val snackbar = com.google.android.material.snackbar.Snackbar.make(view, message, 3000)
    snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
    snackbar.show()
}

/* Format Functions */

fun formatDate(date: Date): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return sdf.format(date)
}

fun formatCustomDate(date: Date): String {
    val sdf = SimpleDateFormat("dd/MM - HH:mm", Locale.getDefault())
    return sdf.format(date)
}

fun stringToDate(date: String): Date? {
    return try {
        val fmt = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        fmt.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}

/* Utils Functions */

fun isDeviceOnline(pContext: Context): Boolean {
    val cm = pContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting
}

