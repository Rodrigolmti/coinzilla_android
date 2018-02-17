package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.R.string
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_exchange_list.viewPoloniex

class ExchangeListActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_list)

        supportActionBar!!.title = getString(string.activity_exchange_list_title)
        enableBackButton()

        viewPoloniex.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            viewPoloniex -> {
                startActivity(Intent(this, ExchangeApiKeyActivity::class.java))
                finish()
            }
        }
    }
}