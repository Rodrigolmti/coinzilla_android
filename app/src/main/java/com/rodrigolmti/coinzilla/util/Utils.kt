package com.rodrigolmti.coinzilla.util

import android.content.Context
import android.net.ConnectivityManager
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

open class Utils {

    companion object {

        fun formatDate(date: Date): String {
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            return sdf.format(date)
        }

        fun formatCustomDate(date: Date): String {
            val sdf = SimpleDateFormat("dd/MM - HH:mm", Locale.getDefault())
            return sdf.format(date)
        }

        open fun stringToDate(date: String): Date? {
            return try {
                val fmt = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                fmt.parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
                null
            }
        }

        open fun isDeviceOnline(pContext: Context): Boolean {
            val cm = pContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }
}