package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.R.string
import com.rodrigolmti.coinzilla.coinzilla.model.api.service.CoinZillaService
import com.rodrigolmti.coinzilla.coinzilla.model.callback.PoloniexBalanceCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.dao.Preferences
import com.rodrigolmti.coinzilla.coinzilla.model.dto.ExchangeAuthDTO
import com.rodrigolmti.coinzilla.coinzilla.model.entity.poloniex.PoloniexBalances
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.PoloniexBalanceAdapter
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.gone
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.visible
import com.rodrigolmti.coinzilla.CZApplication
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_balance.progressBar
import kotlinx.android.synthetic.main.activity_balance.recyclerView

class BalanceActivity : BaseActivity() {

    private val czPreferences: Preferences? = CZApplication.preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)

        enableBackButton()
        title = getString(string.activity_balance_title)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_balance, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (item.itemId) {
                android.R.id.home -> finish()
                R.id.actionRefresh -> { }
                R.id.actionAdd -> startActivity(Intent(this, ExchangeListActivity::class.java))
            }
        }
        return true
    }

    private fun loadData() {
        if (czPreferences!!.poloniexKey == "noData") {
            startActivity(Intent(this, ExchangeListActivity::class.java))
        } else {
            progressBar.visible()
            val exchangeAuth = ExchangeAuthDTO(czPreferences.poloniexKey, czPreferences.poloniexSecret)
            CoinZillaService(this).getAvailableBalances(object : PoloniexBalanceCallBack() {
                override fun onSuccess(data: PoloniexBalances) {
                    recyclerView.layoutManager = LinearLayoutManager(this@BalanceActivity)
                    recyclerView.hasFixedSize()
                    recyclerView.adapter = PoloniexBalanceAdapter(this@BalanceActivity, data.exchange)
                    progressBar.gone()
                }

                override fun onError() {
                    progressBar.gone()
                }
            }, exchangeAuth)
        }
    }
}