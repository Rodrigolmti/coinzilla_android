package com.rodrigolmti.coinzilla.ui.info

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.ObservableField
import com.rodrigolmti.coinzilla.BuildConfig
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerActivity
import com.rodrigolmti.coinzilla.ui.base.navigation.IActivityNavigator
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import javax.inject.Inject

@PerActivity
class InfoViewModel
@Inject constructor(
        @AppContext val context: Context,
        private val resources: Resources,
        private val activityNavigator: IActivityNavigator)
    : BaseViewModel() {

    val onItemSelectedListener: AdapterView.OnItemSelectedListener
    val walletAddress: ObservableField<String> = ObservableField()
    val versionApp: ObservableField<String> = ObservableField()

    init {

        versionApp.set(BuildConfig.VERSION_NAME)

        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val wallet = parent.getItemAtPosition(pos).toString()
                when (wallet) {
                    resources.getString(R.string.activity_info_btc) -> walletAddress.set(resources.getString(R.string.wallet_btc))
                    resources.getString(R.string.activity_info_eth) -> walletAddress.set(resources.getString(R.string.wallet_eth))
                    resources.getString(R.string.activity_info_ltc) -> walletAddress.set(resources.getString(R.string.wallet_ltc))
                }
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }
    }

    fun onClickBack() {
        activityNavigator.finishActivity()
    }

    fun onClickCopy() {
        Toast.makeText(context, resources.getString(R.string.activity_info_toast_wallet), Toast.LENGTH_LONG).show()
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(resources.getString(R.string.activity_info_clipboard), walletAddress.get())
        clipboard.primaryClip = clip
    }

    fun onClickContact() {
        val mailer = Intent(Intent.ACTION_SEND)
        mailer.type = "text/plain"
        mailer.putExtra(Intent.EXTRA_EMAIL, arrayOf(resources.getString(R.string.activity_info_email)))
        mailer.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.activity_info_subject))
        mailer.putExtra(Intent.EXTRA_TEXT, "")
        activityNavigator.startActivity(Intent.createChooser(mailer,
                resources.getString(R.string.activity_info_send_email)))
    }
}