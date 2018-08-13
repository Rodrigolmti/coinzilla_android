package com.rodrigolmti.coinzilla.di.modules

import com.rodrigolmti.coinzilla.data.local.IPreferencesRepository
import com.rodrigolmti.coinzilla.data.local.IRepository
import com.rodrigolmti.coinzilla.data.local.PreferencesRepository
import com.rodrigolmti.coinzilla.data.local.Repository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    internal abstract fun bindCountryRepo(repository: Repository): IRepository

    @Binds
    internal abstract fun bindPrefRepo(preferencesRepository: PreferencesRepository): IPreferencesRepository
}
