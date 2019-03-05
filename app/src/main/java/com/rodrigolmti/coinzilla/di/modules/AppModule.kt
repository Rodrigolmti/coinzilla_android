package com.rodrigolmti.coinzilla.di.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {

    @Provides
    @PerApplication
    @AppContext
    internal fun provideAppContext(): Context = app

    @Provides
    @PerApplication
    internal fun provideResources(): Resources = app.resources
}