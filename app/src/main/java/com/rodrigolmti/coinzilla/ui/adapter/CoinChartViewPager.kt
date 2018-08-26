package com.rodrigolmti.coinzilla.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.ui.coinDetail.chart.CoinChartDataFragment

class CoinChartViewPager(private val tag: String, private val context: Context, fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CoinChartDataFragment.newInstance(tag, context.getString(R.string.activity_detail_usd))
            1 -> CoinChartDataFragment.newInstance(tag, context.getString(R.string.activity_detail_brl))
            else -> CoinChartDataFragment.newInstance(tag, context.getString(R.string.activity_detail_usd))
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return ""
    }

    override fun getCount(): Int {
        return 2
    }
}