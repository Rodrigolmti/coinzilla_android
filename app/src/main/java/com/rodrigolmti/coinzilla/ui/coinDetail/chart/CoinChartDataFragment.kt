package com.rodrigolmti.coinzilla.ui.coinDetail.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.databinding.FragmentCoinChartDataBinding
import com.rodrigolmti.coinzilla.ui.base.BaseFragment

class CoinChartDataFragment : BaseFragment<FragmentCoinChartDataBinding, CoinChartDataViewModel>() {

    companion object {

        const val ARG_TAG = "arg_tag"
        const val ARG_CURRENCY = "arg_currency"

        fun newInstance(tag: String, currency: String): CoinChartDataFragment {
            val fragment = CoinChartDataFragment()
            val bundle = Bundle()
            bundle.putString(ARG_TAG, tag)
            bundle.putString(ARG_CURRENCY, currency)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return setAndBindContentView(layoutInflater, container, savedInstanceState, R.layout.fragment_coin_chart_data)
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
