package com.rodrigolmti.coinzilla.ui.coinDetail.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import com.rodrigolmti.coinzilla.databinding.FragmentCoinInfoBinding
import com.rodrigolmti.coinzilla.ui.base.BaseFragment
import org.parceler.Parcels

class CoinInfoFragment : BaseFragment<FragmentCoinInfoBinding, CoinInfoViewModel>() {

    companion object {

        private const val ARG_COIN = "arg_coin"
        private const val ARG_CURRENCY = "arg_currency"

        fun newInstance(cryptoCurrencyResponse: CryptoCurrencyResponse, currency: String): CoinInfoFragment {
            val fragment = CoinInfoFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_COIN, Parcels.wrap(cryptoCurrencyResponse))
            bundle.putString(ARG_CURRENCY, currency)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return setAndBindContentView(layoutInflater, container, savedInstanceState, R.layout.fragment_coin_info)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val response: CryptoCurrencyResponse = Parcels.unwrap(it.getParcelable(ARG_COIN))
            val currency = it.getString(ARG_CURRENCY)
            viewModel.setupView(response, currency)
        }
    }
}

