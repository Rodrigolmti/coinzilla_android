package com.rodrigolmti.coinzilla.ui.base.navigation

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import com.patloew.countries.ui.base.navigator.IFragmentNavigator

class FragmentNavigator(private val fragment: Fragment) : ActivityNavigator(fragment.activity!!), IFragmentNavigator {

    override fun replaceChildFragment(@IdRes containerId: Int, fragment: Fragment, fragmentTag: String?) {
        replaceFragmentInternal(fragment.childFragmentManager, containerId, fragment, fragmentTag, false, null)
    }

    override fun replaceChildFragmentAndAddToBackStack(@IdRes containerId: Int, fragment: Fragment, fragmentTag: String?, backstackTag: String?) {
        replaceFragmentInternal(fragment.childFragmentManager, containerId, fragment, fragmentTag, true, backstackTag)
    }

    override fun popChildFragmentBackstackImmediate() {
        fragment.childFragmentManager.popBackStackImmediate()
    }
}

