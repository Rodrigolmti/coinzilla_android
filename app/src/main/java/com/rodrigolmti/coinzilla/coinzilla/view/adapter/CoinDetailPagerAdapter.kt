package com.rodrigolmti.coinzilla.coinzilla.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.view.fragment.CoinDetailBRLFragment
import com.rodrigolmti.coinzilla.coinzilla.view.fragment.CoinDetailUSDFragment

/**
 * Created by rodrigolmti on 18/11/17.
 */

class CoinDetailPagerAdapter constructor(fragmentManager: FragmentManager, cryptoCurrency: CryptoCurrency) : FragmentStatePagerAdapter(fragmentManager) {

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
            0 -> "Cotação em Dolar"
            1 -> "Cotação em Real"
            else -> "Cotação em Dolar"
        }
    }

    override fun getCount(): Int {
        return 2
    }
}