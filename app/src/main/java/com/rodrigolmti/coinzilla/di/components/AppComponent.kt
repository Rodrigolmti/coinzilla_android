package com.rodrigolmti.coinzilla.di.components

import android.content.Context
import android.content.res.Resources
import com.rodrigolmti.coinzilla.data.IRepository
import com.rodrigolmti.coinzilla.data.local.IPreferencesHelper
import com.rodrigolmti.coinzilla.data.remote.IApiHelper
import com.rodrigolmti.coinzilla.di.modules.AppModule
import com.rodrigolmti.coinzilla.di.modules.DataModule
import com.rodrigolmti.coinzilla.di.modules.NetModule
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import dagger.Component

@PerApplication
@Component(modules = [AppModule::class, NetModule::class, DataModule::class])
interface AppComponent : AppComponentProvides

interface AppComponentProvides {

    @AppContext
    fun appContext(): Context

    fun resources(): Resources

    fun preferencesHelper(): IPreferencesHelper

    fun remoteApi(): IApiHelper

    fun repository(): IRepository
}