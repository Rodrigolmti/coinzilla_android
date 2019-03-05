package com.rodrigolmti.coinzilla.ui.base.navigation

import androidx.annotation.IdRes

class FragmentNavigator(private val fragment: androidx.fragment.app.Fragment) : ActivityNavigator(fragment.activity!!), IFragmentNavigator {

    override fun replaceChildFragment(@IdRes containerId: Int, fragment: androidx.fragment.app.Fragment, fragmentTag: String?) {
        replaceFragmentInternal(fragment.childFragmentManager, containerId, fragment, fragmentTag, false, null)
    }

    override fun replaceChildFragmentAndAddToBackStack(@IdRes containerId: Int, fragment: androidx.fragment.app.Fragment, fragmentTag: String?, backstackTag: String?) {
        replaceFragmentInternal(fragment.childFragmentManager, containerId, fragment, fragmentTag, true, backstackTag)
    }

    override fun popChildFragmentBackStackImmediate() {
        fragment.childFragmentManager.popBackStackImmediate()
    }
}

