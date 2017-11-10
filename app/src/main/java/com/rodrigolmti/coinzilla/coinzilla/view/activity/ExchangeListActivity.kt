package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.rodrigolmti.coinzilla.R
import kotlinx.android.synthetic.main.activity_exchange_list.*

class ExchangeListActivity: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_list)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Select your exchange"
        supportActionBar!!.setHomeButtonEnabled(true)

        viewPoloniex.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View?) {
        when(view) {
            viewPoloniex -> startActivity(Intent(this, ExchangeApiKeyActivity::class.java))
        }
    }
}