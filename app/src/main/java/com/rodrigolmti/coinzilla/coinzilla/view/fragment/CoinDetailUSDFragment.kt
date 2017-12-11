package com.rodrigolmti.coinzilla.coinzilla.view.fragment

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.model.entity.Exchange
import com.rodrigolmti.coinzilla.coinzilla.model.entity.Historic
import com.rodrigolmti.coinzilla.coinzilla.model.presenter.Presenter
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.ExchangeAdapter
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.formatCurrencyUSD
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.gone
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.visible
import com.rodrigolmti.coinzilla.library.controller.mvp.BasePresenter
import com.rodrigolmti.coinzilla.library.controller.mvp.BaseView
import kotlinx.android.synthetic.main.fragment_coin_detail_brl.view.recyclerView
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.candleChart
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.progressBarExchange
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.progressBarHistoric
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewAvailableSupply
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewErrorExchange
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewMarketCapUsd
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewName
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewPercentChange1h
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewPercentChange24H
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewPercentChange7D
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewPriceBtc
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewPriceUsd
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewSymbol
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewTotalSupply
import kotlinx.android.synthetic.main.fragment_coin_detail_usd.view.textViewVolumeUsd

/**
 * Created by rodrigolmti on 18/11/17.
 */

class CoinDetailUSDFragment : Fragment(), BaseView {

    private lateinit var viewFragment: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewFragment = inflater!!.inflate(R.layout.fragment_coin_detail_usd, container, false)

        val presenter: BasePresenter = Presenter(this, context)

        if (arguments != null) {
            val cryptoCurrency = arguments.getParcelable<CryptoCurrency>("action.coin.detail")
            presenter.historicWeb(cryptoCurrency.symbol!!, getString(R.string.activity_detail_usd))
            presenter.exchangesWeb(cryptoCurrency.symbol!!, getString(R.string.activity_detail_usd))
            viewFragment.textViewPriceUsd.text = cryptoCurrency.priceUsd!!.formatCurrencyUSD()
            viewFragment.textViewAvailableSupply.text = cryptoCurrency.availableSupply
            viewFragment.textViewMarketCapUsd.text = cryptoCurrency.marketCapUsd
            viewFragment.textViewTotalSupply.text = cryptoCurrency.totalSupply
            viewFragment.textViewVolumeUsd.text = cryptoCurrency.volumeUsd
            viewFragment.textViewPriceBtc.text = cryptoCurrency.priceBtc
            viewFragment.textViewSymbol.text = cryptoCurrency.symbol
            viewFragment.textViewName.text = cryptoCurrency.name
            if (cryptoCurrency.percentChange1H!!.contains("-"))
                viewFragment.textViewPercentChange1h.setTextColor(ContextCompat.getColor(context, R.color.alizarin))
            viewFragment.textViewPercentChange1h.text = "${cryptoCurrency.percentChange1H}%"
            if (cryptoCurrency.percentChange24H!!.contains("-"))
                viewFragment.textViewPercentChange24H.setTextColor(ContextCompat.getColor(context, R.color.alizarin))
            viewFragment.textViewPercentChange24H.text = "${cryptoCurrency.percentChange24H}%"
            if (cryptoCurrency.percentChange7D!!.contains("-"))
                viewFragment.textViewPercentChange7D.setTextColor(ContextCompat.getColor(context, R.color.alizarin))
            viewFragment.textViewPercentChange7D.text = "${cryptoCurrency.percentChange7D}%"
        }

        return viewFragment
    }

    override fun success(result: List<Any>) {
        val item: Any = result.first()
        when (item) {
            is Historic -> {
                initChart(result.filterIsInstance<Historic>())
            }
            is Exchange -> {
                loadExchange(result.filterIsInstance<Exchange>())
            }
        }
    }

    private fun loadExchange(result: List<Exchange>) {
        viewFragment.progressBarExchange.gone()
        if (result.isNotEmpty()) {
            viewFragment.recyclerView.layoutManager = LinearLayoutManager(activity)
            viewFragment.recyclerView.hasFixedSize()
            viewFragment.recyclerView.adapter = ExchangeAdapter(activity, result)
            viewFragment.recyclerView.visible()
        } else {
            viewFragment.textViewErrorExchange.visible()
            viewFragment.recyclerView.gone()
        }
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
        candleChart.animate()
        candleChart.data = candleData
        candleChart.invalidate()

        viewFragment.progressBarHistoric.gone()
        candleChart.visible()
    }

    companion object {
        fun newInstance(cryptoCurrency: CryptoCurrency): Fragment {
            val fragment = CoinDetailUSDFragment()
            val args = Bundle()
            args.putParcelable("action.coin.detail", cryptoCurrency)
            fragment.arguments = args
            return fragment
        }
    }
}