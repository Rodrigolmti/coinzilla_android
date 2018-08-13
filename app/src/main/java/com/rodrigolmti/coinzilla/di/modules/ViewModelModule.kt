package com.rodrigolmti.coinzilla.di.modules

import com.rodrigolmti.coinzilla.ui.main.MainActivityViewModel
import com.rodrigolmti.coinzilla.ui.main.MainMvvm
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): MainMvvm
}
