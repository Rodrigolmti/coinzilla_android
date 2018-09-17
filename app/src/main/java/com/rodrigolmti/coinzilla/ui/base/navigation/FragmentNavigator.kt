package com.rodrigolmti.coinzilla.ui.base.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.patloew.countries.ui.base.navigator.IFragmentNavigator

class FragmentNavigator(private val fragment: androidx.fragment.app.Fragment) : ActivityNavigator(fragment.activity!!), IFragmentNavigator {

    override fun replaceChildFragment(@IdRes containerId: Int, fragment: androidx.fragment.app.Fragment, fragmentTag: String?) {
        replaceFragmentInternal(fragment.childFragmentManager, containerId, fragment, fragmentTag, false, null)
    }

    override fun replaceChildFragmentAndAddToBackStack(@IdRes containerId: Int, fragment: androidx.fragment.app.Fragment, fragmentTag: String?, backstackTag: String?) {
        replaceFragmentInternal(fragment.childFragmentManager, containerId, fragment, fragmentTag, true, backstackTag)
    }

    override fun popChildFragmentBackstackImmediate() {
        fragment.childFragmentManager.popBackStackImmediate()
    }
}

