package com.rodrigolmti.coinzilla.data.local

import android.content.Context
import android.preference.PreferenceManager
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import javax.inject.Inject

@PerApplication
class PreferencesRepository
@Inject
constructor(@AppContext context: Context) : IPreferencesRepository {

    private val prefs: android.content.SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override var realmEncryptionKey: ByteArray?
        get() {
            return if (prefs.contains(REALM_ENCRYPTION_KEY)) {
                android.util.Base64.decode(prefs.getString(REALM_ENCRYPTION_KEY, null), android.util.Base64.DEFAULT)
            } else {
                null
            }
        }
        set(key) = prefs.edit().putString(REALM_ENCRYPTION_KEY, android.util.Base64.encodeToString(key, android.util.Base64.DEFAULT)).apply()

    override var authenticationToken: String
        get() = prefs.getString(AUTHENTICATION_TOKEN, "")
        set(value) = prefs.edit().putString(AUTHENTICATION_TOKEN, value).apply()

    override var authenticationTokenTime: Long
        get() = prefs.getLong(AUTHENTICATION_TOKEN_TIME, 0L)
        set(value) = prefs.edit().putLong(AUTHENTICATION_TOKEN_TIME, value).apply()

    override var gpuUpdateTime: Long
        get() = prefs.getLong(GPU_UPDATE_TIME, 0L)
        set(value) = prefs.edit().putLong(GPU_UPDATE_TIME, value).apply()

    override var asicUpdateTime: Long
        get() = prefs.getLong(ASIC_UPDATE_TIME, 0L)
        set(value) = prefs.edit().putLong(ASIC_UPDATE_TIME, value).apply()

    override var altcoinUpdateTime: Long
        get() = prefs.getLong(ALTCOINS_UPDATE_TIME, 0L)
        set(value) = prefs.edit().putLong(ALTCOINS_UPDATE_TIME, value).apply()

    override var cryptocurrencyUpdateTime: Long
        get() = prefs.getLong(CRYPTOCURRENCY_UPDATE_TIME, 0L)
        set(value) = prefs.edit().putLong(CRYPTOCURRENCY_UPDATE_TIME, value).apply()

    companion object {
        private const val REALM_ENCRYPTION_KEY = "realm_encryption_key"
        private const val AUTHENTICATION_TOKEN = "authentication_token"
        private const val AUTHENTICATION_TOKEN_TIME = "authentication_token_time"
        private const val GPU_UPDATE_TIME = "gpu_update_time"
        private const val ASIC_UPDATE_TIME = "asic_update_time"
        private const val ALTCOINS_UPDATE_TIME = "altcoins_update_time"
        private const val CRYPTOCURRENCY_UPDATE_TIME = "cryptocurrency_update_time"
    }
}