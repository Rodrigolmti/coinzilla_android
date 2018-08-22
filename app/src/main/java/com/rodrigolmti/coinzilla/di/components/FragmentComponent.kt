package com.rodrigolmti.coinzilla.di.components

import com.rodrigolmti.coinzilla.di.modules.FragmentModule
import com.rodrigolmti.coinzilla.di.modules.ViewModelModule
import com.rodrigolmti.coinzilla.di.scopes.PerFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ActivityComponent::class], modules = [FragmentModule::class, ViewModelModule::class])
interface FragmentComponent : FragmentComponentProvides {

}

interface FragmentComponentProvides : ActivityComponentProvides
