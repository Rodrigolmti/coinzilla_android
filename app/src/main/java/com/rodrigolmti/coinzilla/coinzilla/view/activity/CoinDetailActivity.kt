package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.CoinDetailPagerAdapter
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_coin_detail.adView
import kotlinx.android.synthetic.main.activity_coin_detail.tabLayout
import kotlinx.android.synthetic.main.activity_coin_detail.viewPager

class CoinDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        enableBackButton()
        removeElevation()
        initAds(adView)

        if (intent.hasExtra("action.coin.detail")) {
            val coin = intent.getParcelableExtra<CryptoCurrency>("action.coin.detail")
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL
            val adapter = CoinDetailPagerAdapter(supportFragmentManager, coin)
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(viewPager)
            title = coin.name
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_coin_detail, menu)
        return true
    }
}