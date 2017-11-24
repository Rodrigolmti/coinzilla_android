package com.rodrigolmti.coinzilla.coinzilla.view.fragment

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.model.entity.Historic
import com.rodrigolmti.coinzilla.coinzilla.model.presenter.Presenter
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.gone
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.visible
import com.rodrigolmti.coinzilla.library.controller.mvp.BasePresenter
import com.rodrigolmti.coinzilla.library.controller.mvp.BaseView
import com.rodrigolmti.coinzilla.library.util.Action
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.candleChart
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.progressBarExchange
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewAvailableSupply
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewName
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewPercentChange1h
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewPercentChange24H
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewPercentChange7D
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewPriceBtc
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewPriceUsd
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewSymbol
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewTotalSupply
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewVolumeUsd
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textviewMarketCapUsd

/**
 * Created by rodrigolmti on 18/11/17.
 */

class CoinDetailUSDFragment : Fragment(), BaseView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_coin_detail_uds, container, false)

        val presenter: BasePresenter = Presenter(this, context)

        if (arguments != null) {
            val cryptoCurrency = arguments.getParcelable<CryptoCurrency>("action.coin.detail")

            presenter.historicWeb(cryptoCurrency.symbol!!, getString(R.string.activity_detail_usd))

            view.textViewName.text = cryptoCurrency.name
            view.textViewSymbol.text = cryptoCurrency.symbol
            view.textViewPriceUsd.text = cryptoCurrency.priceUsd
            view.textViewPriceBtc.text = cryptoCurrency.priceBtc
            view.textViewVolumeUsd.text = cryptoCurrency.volumeUsd
            view.textviewMarketCapUsd.text = cryptoCurrency.marketCapUsd
            view.textViewAvailableSupply.text = cryptoCurrency.availableSupply
            view.textViewTotalSupply.text = cryptoCurrency.totalSupply
            if (cryptoCurrency.percentChange1H!!.contains("-"))
                view.textViewPercentChange1h.setTextColor(ContextCompat.getColor(context, R.color.alizarin))
            view.textViewPercentChange1h.text = "${cryptoCurrency.percentChange1H}%"
            if (cryptoCurrency.percentChange24H!!.contains("-"))
                view.textViewPercentChange24H.setTextColor(ContextCompat.getColor(context, R.color.alizarin))
            view.textViewPercentChange24H.text = "${cryptoCurrency.percentChange24H}%"
            if (cryptoCurrency.percentChange7D!!.contains("-"))
                view.textViewPercentChange7D.setTextColor(ContextCompat.getColor(context, R.color.alizarin))
            view.textViewPercentChange7D.text = "${cryptoCurrency.percentChange7D}%"
        }

        return view
    }

    override fun showProgressBar(visibility: Int) {
    }

    override fun success(action: Action) {
    }

    override fun success(result: List<Any>) {
        val item: Any = result.first()
        when (item) {
            is Historic -> {
                initChart(result.filterIsInstance<Historic>())
            }
        }
    }

    override fun error(action: Action) {
    }

    override fun error(message: String) {
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

        progressBarExchange.gone()
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