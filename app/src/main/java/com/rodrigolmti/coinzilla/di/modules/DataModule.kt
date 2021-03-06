package com.rodrigolmti.coinzilla.di.modules

import com.rodrigolmti.coinzilla.data.IRepository
import com.rodrigolmti.coinzilla.data.Repository
import com.rodrigolmti.coinzilla.data.local.IPreferencesHelper
import com.rodrigolmti.coinzilla.data.local.PreferencesHelper
import com.rodrigolmti.coinzilla.data.remote.ApiHelper
import com.rodrigolmti.coinzilla.data.remote.IApiHelper
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    internal abstract fun bindApiHelper(apiHelper: ApiHelper): IApiHelper

    @Binds
    internal abstract fun bindRepository(repository: Repository): IRepository

    @Binds
    internal abstract fun bindPreferencesHelper(preferencesHelper: PreferencesHelper): IPreferencesHelper
}
