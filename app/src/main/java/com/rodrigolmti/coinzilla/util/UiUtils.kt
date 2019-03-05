package com.rodrigolmti.coinzilla.util

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.rodrigolmti.coinzilla.R

class UiUtils {

    companion object {

        fun showSnackBar(view: View, context: Context, message: String) {
            val snackbar = com.google.android.material.snackbar.Snackbar.make(view, message, 3000)
            snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            snackbar.show()
        }
    }
}