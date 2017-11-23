package com.rodrigolmti.coinzilla.coinzilla.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import kotlinx.android.synthetic.main.fragment_coin_detail_brl.view.textViewMarketCapBrl
import kotlinx.android.synthetic.main.fragment_coin_detail_brl.view.textViewPriceBRL
import kotlinx.android.synthetic.main.fragment_coin_detail_brl.view.textViewVolumeBrl
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewAvailableSupply
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewName
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewPercentChange1h
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewPercentChange24H
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewPercentChange7D
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewPriceBtc
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewSymbol
import kotlinx.android.synthetic.main.fragment_coin_detail_uds.view.textViewTotalSupply

/**
 * Created by rodrigolmti on 18/11/17.
 */

class CoinDetailBRLFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_coin_detail_brl, container, false)

        if (arguments != null) {
            val cryptoCurrency = arguments.getParcelable<CryptoCurrency>("action.coin.detail")
            view.textViewName.text = cryptoCurrency.name
            view.textViewSymbol.text = cryptoCurrency.symbol
            view.textViewPriceBRL.text = cryptoCurrency.priceBrl
            view.textViewPriceBtc.text = cryptoCurrency.priceBtc
            view.textViewVolumeBrl.text = cryptoCurrency.volumeBrl
            view.textViewMarketCapBrl.text = cryptoCurrency.marketCapBrl
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

    companion object {
        fun newInstance(cryptoCurrency: CryptoCurrency): Fragment {
            val fragment = CoinDetailBRLFragment()
            val args = Bundle()
            args.putParcelable("action.coin.detail", cryptoCurrency)
            fragment.arguments = args
            return fragment
        }
    }
}
