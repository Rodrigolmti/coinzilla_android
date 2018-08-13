package com.rodrigolmti.coinzilla.di.modules

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.patloew.countries.ui.base.navigator.IFragmentNavigator
import com.rodrigolmti.coinzilla.di.qualifier.ChildFragmentManager
import com.rodrigolmti.coinzilla.di.scopes.PerFragment
import com.rodrigolmti.coinzilla.ui.base.navigation.FragmentNavigator
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @PerFragment
    @ChildFragmentManager
    internal fun provideChildFragmentManager(): FragmentManager {
        return fragment.childFragmentManager
    }

    @Provides
    @PerFragment
    internal fun provideFragmentNavigator(): IFragmentNavigator {
        return FragmentNavigator(fragment)
    }
}
