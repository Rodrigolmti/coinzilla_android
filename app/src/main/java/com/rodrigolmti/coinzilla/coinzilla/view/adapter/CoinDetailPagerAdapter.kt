package com.rodrigolmti.coinzilla.coinzilla.view.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.rodrigolmti.coinzilla.R.string
import com.rodrigolmti.coinzilla.coinzilla.view.fragment.CoinDetailBRLFragment
import com.rodrigolmti.coinzilla.coinzilla.view.fragment.CoinDetailUSDFragment
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse

/**
* Created by rodrigolmti on 18/11/17.
*/

class CoinDetailPagerAdapter constructor(private val context: Context, fragmentManager: FragmentManager, cryptoCurrency: CryptoCurrencyResponse) : FragmentStatePagerAdapter(fragmentManager) {

    private val coin = cryptoCurrency

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CoinDetailUSDFragment.newInstance(coin)
            1 -> CoinDetailBRLFragment.newInstance(coin)
            else -> CoinDetailUSDFragment.newInstance(coin)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(string.activity_detail_dolar_quote)
            1 -> context.getString(string.activity_detail_real_quote)
            else -> context.getString(string.activity_detail_dolar_quote)
        }
    }

    override fun getCount(): Int {
        return 2
    }
}