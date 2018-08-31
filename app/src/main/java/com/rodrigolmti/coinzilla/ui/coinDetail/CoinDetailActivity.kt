package com.rodrigolmti.coinzilla.ui.coinDetail

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import com.rodrigolmti.coinzilla.databinding.ActivityCoinDetailBinding
import com.rodrigolmti.coinzilla.ui.adapter.FragmentViewPager
import com.rodrigolmti.coinzilla.ui.adapter.ExchangeAdapter
import com.rodrigolmti.coinzilla.ui.base.BaseActivity
import com.rodrigolmti.coinzilla.ui.coinDetail.chart.CoinChartFragment
import com.rodrigolmti.coinzilla.ui.coinDetail.detail.CoinInfoFragment
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : BaseActivity<ActivityCoinDetailBinding, CoinDetailViewModel>() {

    private lateinit var coin: CryptoCurrencyResponse
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAndBindContentView(savedInstanceState, R.layout.activity_coin_detail)
        if (intent.hasExtra("action.coin.detail")) {
            viewModel.getCoinDetailById(intent.getStringExtra("action.coin.detail"))

            viewModel.mutableExchangeList.observe(this, Observer {
                binding.recyclerView.layoutManager = LinearLayoutManager(this@CoinDetailActivity)
                binding.recyclerView.adapter = ExchangeAdapter(this@CoinDetailActivity, it)
                binding.recyclerView.hasFixedSize()
            })

            setupAdapters()
        }

        enableBackButton()
        removeElevation()
        initAds(adView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_coin_detail, menu)
        this.menu = menu
        handleFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_share -> shareCoin()
            R.id.action_favorite -> {
//                coin.favorite = !coin.favorite
//                coinDao.updateCryptoCurrencyFavorite(coin)
//                handleFavoriteIcon()
            }
            else -> finish()
        }

        return true
    }

    private fun setupAdapters() {
        viewModel.cryptoCurrencyResponse.observe(this, Observer { response ->
            response?.let {

                binding.viewPagerChart.adapter = FragmentViewPager(supportFragmentManager,
                        listOf<Fragment>(CoinChartFragment.newInstance(it.tag!!, getString(R.string.activity_detail_usd)),
                                CoinChartFragment.newInstance(it.tag!!, getString(R.string.activity_detail_usd))))
                binding.indicatorChart.setViewPager(binding.viewPagerChart)

                binding.viewPagerInfo.adapter = FragmentViewPager(supportFragmentManager,
                        listOf<Fragment>(CoinInfoFragment.newInstance(response),
                                CoinInfoFragment.newInstance(response)))
                binding.indicatorInfo.setViewPager(binding.viewPagerInfo)

            }
        })
    }

    private fun handleFavoriteIcon() {
//        if (coin.favorite)
//            menu.findItem(R.id.action_favorite).icon = ContextCompat.getDrawable(this, R.drawable.ic_action_favorite_filled)
//        else
//            menu.findItem(R.id.action_favorite).icon = ContextCompat.getDrawable(this, R.drawable.ic_action_favorite)
    }

    private fun shareCoin() {
//        val stringBuilder = StringBuilder()
//        stringBuilder.append("Share by CoinZilla \n")
//        stringBuilder.append("https://play.google.com/store/apps/details?id=com.rodrigolmti.coinzilla&hl=en \n")
//        stringBuilder.append("Coin name: ")
//        stringBuilder.append(coin.name)
//        stringBuilder.append("\n Coin symbol: ")
//        stringBuilder.append(coin.symbol)
//        stringBuilder.append("\n Brl price: ")
//        stringBuilder.append(coin.priceBrl)
//        stringBuilder.append("\n Usd price: ")
//        stringBuilder.append(coin.priceUsd)
//        stringBuilder.append("\n Btc price: ")
//        stringBuilder.append(coin.priceBtc)
//        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
//        sharingIntent.type = "text/plain"
//        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share a coin with")
//        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, stringBuilder.toString())
//        startActivity(Intent.createChooser(sharingIntent, resources.getString(R.string.share)))
    }
}
