package com.rodrigolmti.coinzilla.data.local.prefs

import android.content.Context
import android.preference.PreferenceManager
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import javax.inject.Inject

@PerApplication
class PreferencesHelper
@Inject
constructor(@AppContext context: Context) : IPreferencesHelper {

    companion object {

        private const val REALM_ENCRYPTION_KEY = "realm_encryption_key"
        private const val AUTHENTICATION_TOKEN = "authentication_token"
        private const val AUTHENTICATION_TOKEN_TIME = "authentication_token_time"
        private const val GPU_UPDATE_TIME = "gpu_update_time"
        private const val ASIC_UPDATE_TIME = "asic_update_time"
        private const val ALTCOINS_UPDATE_TIME = "altcoins_update_time"
        private const val CRYPTOCURRENCY_UPDATE_TIME = "cryptocurrency_update_time"
    }

    private val prefs: android.content.SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getRealmEncryptionKey(): ByteArray? {
        return android.util.Base64.decode(prefs.getString(REALM_ENCRYPTION_KEY, null), android.util.Base64.DEFAULT)
    }

    override fun setRealmEncryptionKey(bytes: ByteArray?) {
        prefs.edit().putString(REALM_ENCRYPTION_KEY, android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)).apply()
    }

    override fun getAuthenticationToken(): String {
        return prefs.getString(AUTHENTICATION_TOKEN, "")
    }

    override fun setAuthenticationToken(token: String) {
        prefs.edit().putString(AUTHENTICATION_TOKEN, token).apply()
    }

    override fun getAuthenticationTokenTime(): Long {
        return prefs.getLong(AUTHENTICATION_TOKEN_TIME, 0L)
    }

    override fun setAuthenticationTokenTime(time: Long) {
        prefs.edit().putLong(AUTHENTICATION_TOKEN_TIME, time).apply()
    }

    override fun getGpuUpdateTime(): Long {
        return prefs.getLong(GPU_UPDATE_TIME, 0L)
    }

    override fun setGpuUpdateTime(time: Long) {
        prefs.edit().putLong(GPU_UPDATE_TIME, time).apply()
    }

    override fun getAsicUpdateTime(): Long {
        return prefs.getLong(ASIC_UPDATE_TIME, 0L)
    }

    override fun setAsicUpdateTime(time: Long) {
        prefs.edit().putLong(ASIC_UPDATE_TIME, time).apply()
    }

    override fun getAltcoinUpdateTime(): Long {
        return prefs.getLong(ALTCOINS_UPDATE_TIME, 0L)
    }

    override fun setAltcoinUpdateTime(time: Long) {
        prefs.edit().putLong(ALTCOINS_UPDATE_TIME, time).apply()
    }

    override fun getCryptocurrencyUpdateTime(): Long {
        return prefs.getLong(CRYPTOCURRENCY_UPDATE_TIME, 0L)
    }

    override fun setCryptocurrencyUpdateTime(time: Long) {
        prefs.edit().putLong(CRYPTOCURRENCY_UPDATE_TIME, time).apply()
    }
}