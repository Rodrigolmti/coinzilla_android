package com.rodrigolmti.coinzilla.coinzilla.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.callback.ExchangesCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.callback.HistoricCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.entity.Exchange
import com.rodrigolmti.coinzilla.coinzilla.model.entity.Historic
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.ExchangeAdapter
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import com.rodrigolmti.coinzilla.library.controller.fragment.BaseFragment
import com.rodrigolmti.coinzilla.util.extensions.gone
import com.rodrigolmti.coinzilla.util.extensions.visible
import kotlinx.android.synthetic.main.fragment_coin_detail_brl.*
import kotlinx.android.synthetic.main.fragment_coin_detail_brl.view.*

/**
* Created by rodrigolmti on 18/11/17.
*/

class CoinDetailBRLFragment : BaseFragment() {

    private lateinit var viewFragment: View

    companion object {

        fun newInstance(cryptoCurrency: CryptoCurrencyResponse): BaseFragment {
            val fragment = CoinDetailBRLFragment()
//            val args = Bundle()
//            args.putParcelable("action.coin.detail", cryptoCurrency)
//            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewFragment = inflater.inflate(R.layout.fragment_coin_detail_brl, container, false)

        if (arguments != null) {

//            val cryptoCurrency = arguments!!.getParcelable<CryptoCurrencyResponse>("action.coin.detail")
//            val coinZillaService = CoinZillaService(context!!)
//
//            coinZillaService.getHistoric(callBackHistoric, cryptoCurrency.symbol!!, getString(R.string.activity_detail_brl))
//            coinZillaService.getExchanges(callBackExchanges, cryptoCurrency.symbol!!, getString(R.string.activity_detail_brl))
//
//            viewFragment.textViewPriceBRL.text = cryptoCurrency.priceBrl!!.formatCurrencyBRL()
//            viewFragment.textViewAvailableSupply.text = cryptoCurrency.availableSupply
//            viewFragment.textViewMarketCapBrl.text = cryptoCurrency.marketCapBrl
//            viewFragment.textViewTotalSupply.text = cryptoCurrency.totalSupply
//            viewFragment.textViewVolumeBrl.text = cryptoCurrency.volumeBrl
//            viewFragment.textViewPriceBtc.text = cryptoCurrency.priceBtc
//            viewFragment.textViewSymbol.text = cryptoCurrency.symbol
//            viewFragment.textViewName.text = cryptoCurrency.name
//
//            if (cryptoCurrency.percentChange1H!!.contains("-"))
//                viewFragment.textViewPercentChange1h.setTextColor(ContextCompat.getColor(context!!, R.color.alizarin))
//            viewFragment.textViewPercentChange1h.text = "${cryptoCurrency.percentChange1H}%"
//            if (cryptoCurrency.percentChange24H!!.contains("-"))
//                viewFragment.textViewPercentChange24H.setTextColor(ContextCompat.getColor(context!!, R.color.alizarin))
//            viewFragment.textViewPercentChange24H.text = "${cryptoCurrency.percentChange24H}%"
//            if (cryptoCurrency.percentChange7D!!.contains("-"))
//                viewFragment.textViewPercentChange7D.setTextColor(ContextCompat.getColor(context!!, R.color.alizarin))
//            viewFragment.textViewPercentChange7D.text = "${cryptoCurrency.percentChange7D}%"
        }

        return viewFragment
    }

    private val callBackHistoric: HistoricCallBack = object: HistoricCallBack() {
        override fun onSuccess(list: List<Historic>) {
            viewFragment.progressBarExchange.gone()
            if (list.isNotEmpty()) {
                configureChart(list)
                showChart()
            }
        }

        override fun onError() {
            viewFragment.textViewErrorHistoric.visible()
            viewFragment.progressBarExchange.gone()
        }
    }

    private val callBackExchanges: ExchangesCallBack = object: ExchangesCallBack() {

        override fun onSuccess(list: List<Exchange>) {
            viewFragment.progressBarExchange.gone()

            if (list.isNotEmpty()) {
                viewFragment.recyclerView.layoutManager = LinearLayoutManager(activity)
                viewFragment.recyclerView.hasFixedSize()
                viewFragment.recyclerView.adapter = ExchangeAdapter(context!!, list)
                viewFragment.recyclerView.visibility = View.VISIBLE
                viewFragment.textViewErrorExchange.gone()
            } else {
                viewFragment.textViewErrorExchange.visible()
                viewFragment.recyclerView.gone()
            }
        }

        override fun onError() {
            viewFragment.textViewErrorExchange.visible()
            viewFragment.progressBarHistoric.gone()
            viewFragment.recyclerView.gone()
        }
    }

    private fun showChart() {
        viewFragment.progressBarHistoric.gone()
        candleChart.visible()
    }
}
