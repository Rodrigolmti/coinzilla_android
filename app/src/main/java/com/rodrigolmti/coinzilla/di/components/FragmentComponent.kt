package com.rodrigolmti.coinzilla.di.components

import com.rodrigolmti.coinzilla.di.modules.FragmentModule
import com.rodrigolmti.coinzilla.di.modules.ViewModelModule
import com.rodrigolmti.coinzilla.di.scopes.PerFragment
import com.rodrigolmti.coinzilla.ui.coinDetail.chart.CoinChartFragment
import com.rodrigolmti.coinzilla.ui.coinDetail.detail.CoinInfoFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ActivityComponent::class], modules = [FragmentModule::class, ViewModelModule::class])
interface FragmentComponent : FragmentComponentProvides {

    fun inject(fragment: CoinChartFragment)

    fun inject(fragment: CoinInfoFragment)
}

interface FragmentComponentProvides : ActivityComponentProvides
