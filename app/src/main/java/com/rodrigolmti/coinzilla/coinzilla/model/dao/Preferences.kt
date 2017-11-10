package com.rodrigolmti.coinzilla.coinzilla.model.dao

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    private val UPDATE_DATE_CRYPTO_CURRENCY = "updateDateCryptoCurrency"
    private val UPDATE_DATE_ASIC = "updateDateAsic"
    private val COIN_PREFERENCES = "coinPreferences"
    private val UPDATE_DATE_WARZ = "updateDateWarz"
    private val IDENTIFICATION = "identification"
    private val UPDATE_DATE_GPU = "updateDateGpu"
    private val POLONIEX_KEY = "poloniexKey"
    private val POLONIEX_SECRET = "poloniexSecret"
    private val TOKEN_DATE = "token_date"
    private val TOKEN = "token"
    private val preferences: SharedPreferences = context.getSharedPreferences(COIN_PREFERENCES, 0)

    var updateDateGpu: String
        get() = preferences.getString(UPDATE_DATE_GPU, "noData")
        set(date) = preferences.edit().putString(UPDATE_DATE_GPU, date).apply()

    var updateDateAsic: String
        get() = preferences.getString(UPDATE_DATE_ASIC, "noData")
        set(date) = preferences.edit().putString(UPDATE_DATE_ASIC, date).apply()

    var updateDateWarz: String
        get() = preferences.getString(UPDATE_DATE_WARZ, "noData")
        set(date) = preferences.edit().putString(UPDATE_DATE_WARZ, date).apply()

    var token: String
        get() = preferences.getString(TOKEN, "noData")
        set(date) = preferences.edit().putString(TOKEN, date).apply()

    var tokenDate: String
        get() = preferences.getString(TOKEN_DATE, "noData")
        set(date) = preferences.edit().putString(TOKEN_DATE, date).apply()

    var identification: String
        get() = preferences.getString(IDENTIFICATION, "noData")
        set(date) = preferences.edit().putString(IDENTIFICATION, date).apply()

    var updateDateCryptoCurrency: String
        get() = preferences.getString(UPDATE_DATE_CRYPTO_CURRENCY, "noData")
        set(date) = preferences.edit().putString(UPDATE_DATE_CRYPTO_CURRENCY, date).apply()

    var poloniexKey: String
        get() = preferences.getString(POLONIEX_KEY, "noData")
        set(date) = preferences.edit().putString(POLONIEX_KEY, date).apply()

    var poloniexSecret: String
        get() = preferences.getString(POLONIEX_SECRET, "noData")
        set(date) = preferences.edit().putString(POLONIEX_SECRET, date).apply()
}