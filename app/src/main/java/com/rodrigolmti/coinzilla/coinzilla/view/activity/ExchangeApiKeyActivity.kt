package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.dao.Preferences
import com.rodrigolmti.coinzilla.library.app.CZApplication
import com.rodrigolmti.coinzilla.library.util.Utils
import kotlinx.android.synthetic.main.activity_exchange_api_key.buttonContact
import kotlinx.android.synthetic.main.activity_exchange_api_key.editTextApiKey
import kotlinx.android.synthetic.main.activity_exchange_api_key.editTextApiSecret

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
            editTextApiKey.requestFocus()
            error = true
        }
        if (editTextApiSecret.text.isEmpty()) {
            editTextApiSecret.error = "Enter your api secret"
            editTextApiSecret.requestFocus()
            error = true
        }

        if (!error) {
            czPreferences!!.poloniexKey = editTextApiKey.text.toString()
            czPreferences.poloniexSecret = editTextApiSecret.text.toString()
            finish()
        }
    }
}