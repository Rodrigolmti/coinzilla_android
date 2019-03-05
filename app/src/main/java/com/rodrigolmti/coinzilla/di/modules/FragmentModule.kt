package com.rodrigolmti.coinzilla.di.modules

import com.rodrigolmti.coinzilla.di.qualifier.ChildFragmentManager
import com.rodrigolmti.coinzilla.di.scopes.PerFragment
import com.rodrigolmti.coinzilla.ui.base.navigation.FragmentNavigator
import com.rodrigolmti.coinzilla.ui.base.navigation.IFragmentNavigator
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: androidx.fragment.app.Fragment) {

    @Provides
    @PerFragment
    @ChildFragmentManager
    internal fun provideChildFragmentManager(): androidx.fragment.app.FragmentManager {
        return fragment.childFragmentManager
    }

    @Provides
    @PerFragment
    internal fun provideFragmentNavigator(): IFragmentNavigator {
        return FragmentNavigator(fragment)
    }
}
