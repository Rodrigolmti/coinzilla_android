package com.rodrigolmti.coinzilla

import android.content.res.Resources
import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.google.android.gms.ads.MobileAds
import com.rodrigolmti.coinzilla.di.components.AppComponent
import com.rodrigolmti.coinzilla.di.components.DaggerAppComponent
import com.rodrigolmti.coinzilla.di.modules.AppModule
import com.squareup.leakcanary.LeakCanary
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class CZApplication : MultiDexApplication() {

    companion object {

        lateinit var instance: CZApplication
            private set

        lateinit var appComponent: AppComponent
            private set

        val res: Resources
            get() = instance.resources
    }

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
        MobileAds.initialize(this, getString(R.string.admob_api_key))

        if (LeakCanary.isInAnalyzerProcess(this)) return

        instance = this
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        Timber.plant(Timber.DebugTree())
    }
}
