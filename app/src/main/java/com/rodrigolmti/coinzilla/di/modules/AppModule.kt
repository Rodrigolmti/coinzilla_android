package com.rodrigolmti.coinzilla.di.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class AppModule(private val app: Application) {

    @Provides
    @PerApplication
    @AppContext
    internal fun provideAppContext(): Context = app

    @Provides
    @PerApplication
    internal fun provideResources(): Resources = app.resources

    @Provides
    @PerApplication
    internal fun provideRefWatcher(): RefWatcher = LeakCanary.install(app)

    @Provides
    internal fun provideRealm(): Realm = Realm.getDefaultInstance()
}