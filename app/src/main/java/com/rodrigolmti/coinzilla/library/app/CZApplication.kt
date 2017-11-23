package com.rodrigolmti.coinzilla.library.app

import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.google.android.gms.ads.MobileAds
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.dao.Preferences
import io.fabric.sdk.android.Fabric
import io.realm.Realm
import io.realm.RealmConfiguration

class CZApplication : MultiDexApplication() {

    companion object {
        var preferences: Preferences? = null
    }

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
        MobileAds.initialize(this, getString(R.string.admob_api_key))

        Realm.init(this)
        val config: RealmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)

        preferences = Preferences(applicationContext)
    }
}
