package com.rodrigolmti.coinzilla.di.components

import com.rodrigolmti.coinzilla.coinzilla.view.fragment.CoinDetailBRLFragment
import com.rodrigolmti.coinzilla.coinzilla.view.fragment.CoinDetailUSDFragment
import com.rodrigolmti.coinzilla.di.modules.FragmentModule
import com.rodrigolmti.coinzilla.di.modules.ViewModelModule
import com.rodrigolmti.coinzilla.di.scopes.PerFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ActivityComponent::class], modules = [FragmentModule::class, ViewModelModule::class])
interface FragmentComponent : FragmentComponentProvides {

    fun inject(fragment: CoinDetailBRLFragment)

    fun inject(fragment: CoinDetailUSDFragment)
}

interface FragmentComponentProvides : ActivityComponentProvides
