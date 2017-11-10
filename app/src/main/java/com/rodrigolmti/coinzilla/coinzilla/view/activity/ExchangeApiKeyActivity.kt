package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.dao.Preferences
import com.rodrigolmti.coinzilla.library.app.CZApplication
import com.rodrigolmti.coinzilla.library.util.Utils
import kotlinx.android.synthetic.main.activity_exchange_api_key.*

class ExchangeApiKeyActivity: AppCompatActivity(), View.OnClickListener {

    private val czPreferences: Preferences? = CZApplication.preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_api_key)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = getString(R.string.activity_exchange_api_title)
        supportActionBar!!.setHomeButtonEnabled(true)

        buttonContact.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        var error = false
        if (editTextApiKey.text.isEmpty()) {
            editTextApiKey.error = "Enter your api key"
            error = true
        }
        if (editTextApiSecret.text.isNotEmpty()) {
            editTextApiSecret.error = "Enter your api secret"
            error = true
        }

        if (!error) {
            val secretDigest: String = Utils().hmacDigest("command=returnBalances&nonce=1503788021328002", editTextApiSecret.text.toString())
            czPreferences!!.poloniexKey = editTextApiKey.text.toString()
            czPreferences.poloniexSecret = secretDigest
        }
    }
}