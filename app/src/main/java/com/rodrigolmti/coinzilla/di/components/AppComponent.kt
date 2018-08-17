package com.rodrigolmti.coinzilla.di.components

import android.content.Context
import android.content.res.Resources
import com.rodrigolmti.coinzilla.data.IRepository
import com.rodrigolmti.coinzilla.data.local.prefs.IPreferencesHelper
import com.rodrigolmti.coinzilla.data.remote.IApiHelper
import com.rodrigolmti.coinzilla.di.modules.AppModule
import com.rodrigolmti.coinzilla.di.modules.DataModule
import com.rodrigolmti.coinzilla.di.modules.NetModule
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import com.squareup.leakcanary.RefWatcher
import dagger.Component

@PerApplication
@Component(modules = [AppModule::class, NetModule::class, DataModule::class])
interface AppComponent : AppComponentProvides

interface AppComponentProvides {

    @AppContext
    fun appContext(): Context

    fun resources(): Resources

    fun refWatcher(): RefWatcher

    fun preferencesHelper(): IPreferencesHelper

    fun remoteApi(): IApiHelper

    fun repository(): IRepository
}