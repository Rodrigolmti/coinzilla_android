package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.google.android.gms.ads.AdRequest
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.model.entity.Exchange
import com.rodrigolmti.coinzilla.coinzilla.model.entity.Historic
import com.rodrigolmti.coinzilla.coinzilla.model.presenter.Presenter
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.ExchangeAdapter
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import com.rodrigolmti.coinzilla.library.controller.mvp.BasePresenter
import com.rodrigolmti.coinzilla.library.controller.mvp.BaseView
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.library.util.Utils
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(), View.OnClickListener, BaseView {

    private val presenter: BasePresenter = Presenter(this, this)
    private var exchangesUsd: List<Exchange> = ArrayList()
    private var exchangesBrl: List<Exchange> = ArrayList()
    private var historicUsd: List<Historic> = ArrayList()
    private var historicBrl: List<Historic> = ArrayList()
    private var coin: CryptoCurrency? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        toggleButtonBrl.setOnClickListener(this)
        toggleButtonUsd.setOnClickListener(this)

        if (intent.hasExtra("action.coin")) {

            coin = intent.getParcelableExtra("action.coin")
            coin?.let {

                if (Utils().isDeviceOnline(this)) {
                    presenter.exchangesWeb(it.symbol!!, getString(R.string.activity_detail_usd))
                    presenter.historicWeb(it.symbol!!, getString(R.string.activity_detail_usd))
                } else {
                    textViewErrorExchange.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                    textViewErrorHistoric.visibility = View.GONE
                    candleChart.visibility = View.GONE

                    textViewErrorHistoric.visibility = View.VISIBLE
                    textViewErrorHistoric.text = getString(R.string.general_error_connection)
                    textViewErrorExchange.visibility = View.VISIBLE
                    textViewErrorExchange.text = getString(R.string.general_error_connection)
                }

                supportActionBar!!.title = it.name
                textViewName.text = it.name
                textViewSymbol.text = it.symbol
                textViewPriceUsd.text = it.priceUsd
                textViewPriceBtc.text = it.priceBtc
                textViewPriceBrl.text = it.priceBrl
                textViewVolumeUsd.text = it.volumeUsd
                textViewVolumeBrl.text = it.volumeBrl
                textviewMarketCapUsd.text = it.marketCapUsd
                textViewMarketCapBrl.text = it.marketCapBrl
                textViewAvailableSupply.text = it.availableSupply
                textViewTotalSupply.text = it.totalSupply
                if (it.percentChange1H!!.contains("-"))
                    textViewPercentChange1h.setTextColor(ContextCompat.getColor(this, R.color.alizarin))
                textViewPercentChange1h.text = "${it.percentChange1H}%"
                if (it.percentChange24H!!.contains("-"))
                    textViewPercentChange24H.setTextColor(ContextCompat.getColor(this, R.color.alizarin))
                textViewPercentChange24H.text = "${it.percentChange24H}%"
                if (it.percentChange7D!!.contains("-"))
                    textViewPercentChange7D.setTextColor(ContextCompat.getColor(this, R.color.alizarin))
                textViewPercentChange7D.text = "${it.percentChange7D}%"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (coin!!.favorite) {
            menuInflater.inflate(R.menu.menu_favorite_ative, menu)
        } else {
            menuInflater.inflate(R.menu.menu_favorite, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_favorite -> {
                if (coin!!.favorite) {
                    coin!!.favorite = false
                    presenter.updateCryptoCurrencyFavorite(coin!!)
                } else {
                    coin!!.favorite = true
                    presenter.updateCryptoCurrencyFavorite(coin!!)
                }
            }
            else -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProgressBar(visibility: Int) {
        progressBarHistoric.visibility = visibility
        progressBarExchange.visibility = visibility
    }

    override fun success(action: Action) {
    }

    override fun success(result: List<Any>) {
        val item: Any = result.first()
        when (item) {
            is Exchange -> {
                if (exchangesUsd.isEmpty()) {
                    exchangesUsd = result.filterIsInstance<Exchange>()
                    loadExchange(exchangesUsd)
                } else {
                    exchangesBrl = result.filterIsInstance<Exchange>()
                    loadExchange(exchangesBrl)
                }
            }
            is Historic -> {
                if (historicUsd.isEmpty()) {
                    historicUsd = result.filterIsInstance<Historic>()
                    initChart(historicUsd)
                } else {
                    historicBrl = result.filterIsInstance<Historic>()
                    initChart(historicBrl)
                }
            }
        }
    }

    override fun error(action: Action) {
        when (action) {
            Action.EXCHANGE -> {
                textViewErrorExchange.visibility = View.VISIBLE
                progressBarExchange.visibility = View.GONE
                recyclerView.visibility = View.GONE
            }
            Action.HISTORIC -> {
                textViewErrorHistoric.visibility = View.VISIBLE
                progressBarHistoric.visibility = View.GONE
                candleChart.visibility = View.GONE
            }
        }
    }

    override fun error(message: String) {
    }

    override fun onClick(view: View?) {
        if (Utils().isDeviceOnline(this)) {
            coin?.let {

                textViewErrorExchange.visibility = View.GONE
                recyclerView.visibility = View.GONE

                textViewErrorHistoric.visibility = View.GONE
                candleChart.visibility = View.GONE

                when (view) {
                    toggleButtonBrl -> {
                        toggleButtonUsd.isChecked = false
                        if (exchangesBrl.isEmpty()) {
                            presenter.exchangesWeb(it.symbol!!, getString(R.string.activity_detail_brl))
                        } else {
                            loadExchange(exchangesBrl)
                        }
                        if (historicBrl.isEmpty()) {
                            presenter.historicWeb(it.symbol!!, getString(R.string.activity_detail_brl))
                        } else {
                            initChart(historicBrl)
                        }
                    }
                    toggleButtonUsd -> {
                        toggleButtonBrl.isChecked = false
                        if (exchangesUsd.isEmpty()) {
                            presenter.exchangesWeb(it.symbol!!, getString(R.string.activity_detail_usd))
                        } else {
                            loadExchange(exchangesUsd)
                        }
                        if (historicUsd.isEmpty()) {
                            presenter.historicWeb(it.symbol!!, getString(R.string.activity_detail_usd))
                        } else {
                            initChart(historicUsd)
                        }
                    }
                }
            }
        } else {

            textViewErrorExchange.visibility = View.GONE
            recyclerView.visibility = View.GONE

            textViewErrorHistoric.visibility = View.GONE
            candleChart.visibility = View.GONE

            textViewErrorHistoric.visibility = View.VISIBLE
            textViewErrorHistoric.text = getString(R.string.general_error_connection)
            textViewErrorExchange.visibility = View.VISIBLE
            textViewErrorExchange.text = getString(R.string.general_error_connection)
        }
    }

    private fun loadExchange(result: List<Exchange>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = ExchangeAdapter(this, result)
        textViewErrorExchange.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        progressBarExchange.visibility = View.GONE
    }

    private fun initChart(result: List<Historic>) {
        candleChart.resetTracking()
        candleChart.setBackgroundColor(Color.WHITE)
        candleChart.description.isEnabled = false
        candleChart.setMaxVisibleValueCount(60)
        candleChart.setPinchZoom(false)
        candleChart.setTouchEnabled(false)
        candleChart.setDrawGridBackground(false)

        val xAxis = candleChart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawLabels(false)
        xAxis.setDrawGridLines(false)

        val leftAxis = candleChart.axisLeft
        leftAxis.setLabelCount(7, false)
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawAxisLine(false)

        val rightAxis = candleChart.axisRight
        rightAxis.isEnabled = false

        candleChart.legend.isEnabled = false

        val entries: ArrayList<CandleEntry> = ArrayList()
        var count = 0f
        for (obj in result) {
            count++
            entries.add(CandleEntry(count, obj.high, obj.low, obj.open, obj.close))
        }
        val dataSet = CandleDataSet(entries, "Historic")
        dataSet.color = Color.rgb(80, 80, 80)
        dataSet.shadowColor = Color.DKGRAY
        dataSet.setDrawValues(false)
        dataSet.shadowWidth = 0.7f
        dataSet.decreasingColor = Color.rgb(192, 57, 43)
        dataSet.decreasingPaintStyle = Paint.Style.FILL
        dataSet.increasingColor = Color.rgb(39, 174, 96)
        dataSet.increasingPaintStyle = Paint.Style.FILL
        dataSet.setDrawIcons(false)
        dataSet.neutralColor = Color.BLUE
        dataSet.valueTextColor = Color.RED
        val candleData = CandleData(dataSet)
        candleChart.data = candleData
        candleChart.invalidate()

        textViewErrorHistoric.visibility = View.GONE
        candleChart.visibility = View.VISIBLE
        progressBarHistoric.visibility = View.GONE
    }
}