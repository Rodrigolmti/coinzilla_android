package com.rodrigolmti.coinzilla.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class FragmentViewPager(
        fragmentManager: FragmentManager,
        private val fragments: List<Fragment>)
    : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return ""
    }

    override fun getCount(): Int {
        return fragments.size
    }
}