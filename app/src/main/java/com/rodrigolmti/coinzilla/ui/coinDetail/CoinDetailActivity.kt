package com.rodrigolmti.coinzilla.ui.coinDetail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import com.rodrigolmti.coinzilla.databinding.ActivityCoinDetailBinding
import com.rodrigolmti.coinzilla.ui.adapter.ExchangeAdapter
import com.rodrigolmti.coinzilla.ui.adapter.FragmentViewPager
import com.rodrigolmti.coinzilla.ui.base.BaseActivity
import com.rodrigolmti.coinzilla.ui.coinDetail.chart.CoinChartFragment
import com.rodrigolmti.coinzilla.ui.coinDetail.detail.CoinInfoFragment
import com.rodrigolmti.coinzilla.util.formatCurrencyBRL
import com.rodrigolmti.coinzilla.util.formatCurrencyUSD

class CoinDetailActivity : BaseActivity<ActivityCoinDetailBinding, CoinDetailViewModel>() {

    private var coin: CryptoCurrencyResponse? = null
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAndBindContentView(R.layout.activity_coin_detail)

        if (intent.hasExtra("action.coin.detail")) {

            viewModel.getCoinDetailById(intent.getStringExtra("action.coin.detail"))

            viewModel.mutableExchangeList.observe(this, Observer {
                binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@CoinDetailActivity)
                binding.recyclerView.adapter = ExchangeAdapter(this@CoinDetailActivity, it)
                binding.recyclerView.hasFixedSize()
            })

            setupAdapters()
        }

        enableBackButton()
        removeElevation()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_coin_detail, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_share -> shareCoin()
            else -> finish()
        }

        return true
    }

    private fun setupAdapters() {
        viewModel.cryptoCurrencyResponse.observe(this, Observer { response ->
            response?.let {

                val usd = getString(R.string.activity_detail_usd)
                val brl = getString(R.string.activity_detail_brl)

                binding.viewPagerChart.adapter = FragmentViewPager(supportFragmentManager,
                        listOf<androidx.fragment.app.Fragment>(
                                CoinChartFragment.newInstance(it.tag!!, usd),
                                CoinChartFragment.newInstance(it.tag!!, brl))
                )
                binding.indicatorChart.setViewPager(binding.viewPagerChart)

                binding.viewPagerInfo.adapter = FragmentViewPager(supportFragmentManager,
                        listOf<androidx.fragment.app.Fragment>(
                                CoinInfoFragment.newInstance(response, usd),
                                CoinInfoFragment.newInstance(response, brl))
                )
                binding.indicatorInfo.setViewPager(binding.viewPagerInfo)

                title = it.name
                coin = it
            }
        })
    }

    private fun shareCoin() {
        coin?.let {
            val stringBuilder = StringBuilder()
            stringBuilder.append("Share by CoinZilla \n")
            stringBuilder.append("https://play.google.com/store/apps/details?id=com.rodrigolmti.coinzilla&hl=en \n")
            stringBuilder.append("Coin name: ")
            stringBuilder.append(it.name)
            stringBuilder.append("\nCoin symbol: ")
            stringBuilder.append(it.tag)
            stringBuilder.append("\nBrl price: R$")
            stringBuilder.append(it.quoteBrl?.price?.formatCurrencyBRL())
            stringBuilder.append("\nUsd price: $")
            stringBuilder.append(it.quoteUsd?.price?.formatCurrencyUSD())
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share a coin with")
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, stringBuilder.toString())
            startActivity(Intent.createChooser(sharingIntent, resources.getString(R.string.share)))
        }
    }
}
