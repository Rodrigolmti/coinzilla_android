package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.dao.CoinDAO
import com.rodrigolmti.coinzilla.coinzilla.model.entity.coin.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.CoinDetailPagerAdapter
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_coin_detail.adView
import kotlinx.android.synthetic.main.activity_coin_detail.tabLayout
import kotlinx.android.synthetic.main.activity_coin_detail.viewPager

class CoinDetailActivity : BaseActivity() {

    private lateinit var coin: CryptoCurrency
    private val coinDao: CoinDAO = CoinDAO()
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        enableBackButton()
        removeElevation()
        initAds(adView)

        if (intent.hasExtra("action.coin.detail")) {
            coin = intent.getParcelableExtra("action.coin.detail")
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL
            val adapter = CoinDetailPagerAdapter(this, supportFragmentManager, coin)
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(viewPager)
            title = coin.name
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_coin_detail, menu)
        this.menu = menu
        handleFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> finish()
            R.id.action_share -> shareCoin()
            R.id.action_favorite -> {
                coin.favorite = !coin.favorite
                coinDao.updateCryptoCurrencyFavorite(coin)
                handleFavoriteIcon()
            }
            else -> finish()
        }

        return true
    }

    private fun handleFavoriteIcon() {
        if (coin.favorite)
            menu.findItem(R.id.action_favorite).icon = ContextCompat.getDrawable(this, R.drawable.ic_action_favorite_filled)
        else
            menu.findItem(R.id.action_favorite).icon = ContextCompat.getDrawable(this, R.drawable.ic_action_favorite)
    }

    private fun shareCoin() {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Share by CoinZilla \n")
        stringBuilder.append("https://play.google.com/store/apps/details?id=com.rodrigolmti.coinzilla&hl=en \n")
        stringBuilder.append("Coin name: ")
        stringBuilder.append(coin.name)
        stringBuilder.append("\n Coin symbol: ")
        stringBuilder.append(coin.symbol)
        stringBuilder.append("\n Brl price: ")
        stringBuilder.append(coin.priceBrl)
        stringBuilder.append("\n Usd price: ")
        stringBuilder.append(coin.priceUsd)
        stringBuilder.append("\n Btc price: ")
        stringBuilder.append(coin.priceBtc)
        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share a coin with")
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, stringBuilder.toString())
        startActivity(Intent.createChooser(sharingIntent, resources.getString(R.string.share)))
    }
}
