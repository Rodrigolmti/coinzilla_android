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
        try {
            val fmt = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
            return fmt.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            return null
        }
    }

    open fun isDeviceOnline(pContext: Context): Boolean {
        val cm = pContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    open fun hmacDigest(value: String, secret: String): String {
        var result: String
        try {
            val hmacSHA512 = Mac.getInstance("HmacSHA512")
            val secretKeySpec = SecretKeySpec(secret.toByteArray(),
                    "HmacSHA512")
            hmacSHA512.init(secretKeySpec)

            val digest = hmacSHA512.doFinal(value.toByteArray())
            val hash = BigInteger(1, digest)
            result = hash.toString(16)
            if (result.length % 2 != 0) {
                result = "0" + result
            }
        } catch (ex: IllegalStateException) {
            throw RuntimeException("Problemas calculando HMAC", ex)
        } catch (ex: InvalidKeyException) {
            throw RuntimeException("Problemas calculando HMAC", ex)
        } catch (ex: NoSuchAlgorithmException) {
            throw RuntimeException("Problemas calculando HMAC", ex)
        }
        return result
    }

    open fun showSnackBar(view: View, context: Context, message: String) {
        val snackbar = Snackbar.make(view, message, 3000)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        snackbar.show()
    }
}