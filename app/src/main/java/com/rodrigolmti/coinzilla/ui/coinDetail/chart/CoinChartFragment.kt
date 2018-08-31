package com.rodrigolmti.coinzilla.ui.coinDetail.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.databinding.FragmentCoinChartBinding
import com.rodrigolmti.coinzilla.ui.base.BaseFragment

class CoinChartFragment : BaseFragment<FragmentCoinChartBinding, CoinChartViewModel>() {

    companion object {

        private const val ARG_TAG = "arg_tag"
        private const val ARG_CURRENCY = "arg_currency"

        fun newInstance(tag: String, currency: String): CoinChartFragment {
            val fragment = CoinChartFragment()
            val bundle = Bundle()
            bundle.putString(ARG_TAG, tag)
            bundle.putString(ARG_CURRENCY, currency)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return setAndBindContentView(layoutInflater, container, savedInstanceState, R.layout.fragment_coin_chart)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val tag = it.getString(ARG_TAG)
            val currency = it.getString(ARG_CURRENCY)
            viewModel.getCoinHistorical(tag, currency)
        }
    }
}
