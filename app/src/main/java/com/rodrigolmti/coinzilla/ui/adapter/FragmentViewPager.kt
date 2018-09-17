package com.rodrigolmti.coinzilla.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentViewPager(
        fragmentManager: androidx.fragment.app.FragmentManager,
        private val fragments: List<androidx.fragment.app.Fragment>)
    : androidx.fragment.app.FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return ""
    }

    override fun getCount(): Int {
        return fragments.size
    }
}