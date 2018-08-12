package com.rodrigolmti.coinzilla.library.util

import android.content.Context
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import com.rodrigolmti.coinzilla.R
import kotlinx.android.synthetic.main.activity_list.content
import java.math.BigInteger
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

open class Utils {

    open fun formatDate(date: Date): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return sdf.format(date)
    }

    open fun formatCustomDate(date: Date): String {
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

    open fun showSnackBar(view: View, context: Context, message: String) {
        val snackbar = Snackbar.make(view, message, 3000)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        snackbar.show()
    }
}