package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import com.google.android.gms.ads.AdRequest
import com.rodrigolmti.coinzilla.BuildConfig
import com.rodrigolmti.coinzilla.R
import kotlinx.android.synthetic.main.activity_info.*
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import android.content.Intent

class InfoActivity: Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        initAds()

        textViewVersion.text = BuildConfig.VERSION_NAME
        imageViewBack.setOnClickListener(this)
        clickViewBack.setOnClickListener(this)
        buttonContact.setOnClickListener(this)
        buttonCopy.setOnClickListener(this)

        spinnerWallets.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val wallet = parent.getItemAtPosition(pos).toString()
                when (wallet) {
                    getString(R.string.activity_info_btc) -> textViewWallet.text = getString(R.string.wallet_btc)
                    getString(R.string.activity_info_eth) -> textViewWallet.text = getString(R.string.wallet_eth)
                    getString(R.string.activity_info_ltc) -> textViewWallet.text = getString(R.string.wallet_ltc)
                }
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            buttonCopy -> {
                Toast.makeText(this, getString(R.string.activity_info_toast_wallet), Toast.LENGTH_LONG).show()
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText(getString(R.string.activity_info_clipboard), textViewWallet.text)
                clipboard.primaryClip = clip
            }
            buttonContact -> {
                val mailer = Intent(Intent.ACTION_SEND)
                mailer.type = "text/plain"
                mailer.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.activity_info_email)))
                mailer.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.activity_info_subject))
                mailer.putExtra(Intent.EXTRA_TEXT, "")
                startActivity(Intent.createChooser(mailer, getString(R.string.activity_info_send_email)))
            }
            else -> {
                finish()
            }
        }
    }

    private fun initAds() {
        val adRequest = AdRequest.Builder()
                .addTestDevice(getString(R.string.admob_test_device_genymotion))
                .addTestDevice(getString(R.string.admob_test_device_one_plus))
                .addTestDevice(getString(R.string.admob_test_device_s7))
                .build()
        adView.loadAd(adRequest)
    }
}