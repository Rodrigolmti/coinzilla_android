package com.rodrigolmti.coinzilla.di.components

import android.content.Context
import android.content.res.Resources
import com.rodrigolmti.coinzilla.data.local.IPreferencesRepository
import com.rodrigolmti.coinzilla.data.local.IRepository
import com.rodrigolmti.coinzilla.data.remote.IRemoteApi
import com.rodrigolmti.coinzilla.di.modules.AppModule
import com.rodrigolmti.coinzilla.di.modules.DataModule
import com.rodrigolmti.coinzilla.di.modules.NetModule
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import com.squareup.leakcanary.RefWatcher
import dagger.Component
import io.realm.Realm

@PerApplication
@Component(modules = [AppModule::class, NetModule::class, DataModule::class])
interface AppComponent : AppComponentProvides

interface AppComponentProvides {

    @AppContext
    fun appContext(): Context

    fun resources(): Resources

    fun refWatcher(): RefWatcher

    fun realm(): Realm

    fun repository(): IRepository

    fun preferencesRepository(): IPreferencesRepository

    fun remoteApi(): IRemoteApi
}