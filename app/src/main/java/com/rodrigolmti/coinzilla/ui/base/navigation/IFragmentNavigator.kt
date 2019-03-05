package com.rodrigolmti.coinzilla.ui.base.navigation

import androidx.annotation.IdRes

interface IFragmentNavigator : IActivityNavigator {

    fun replaceChildFragment(@IdRes containerId: Int, fragment: androidx.fragment.app.Fragment, fragmentTag: String? = null)

    fun replaceChildFragmentAndAddToBackStack(@IdRes containerId: Int, fragment: androidx.fragment.app.Fragment, fragmentTag: String?, backstackTag: String?)

    fun popChildFragmentBackStackImmediate()

}
