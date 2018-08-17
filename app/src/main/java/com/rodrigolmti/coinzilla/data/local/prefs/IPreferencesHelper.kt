package com.rodrigolmti.coinzilla.data.local.prefs

interface IPreferencesHelper {

    fun getAuthenticationToken(): String

    fun setAuthenticationToken(token: String)

    fun getAuthenticationTokenTime(): Long

    fun setAuthenticationTokenTime(time: Long)

    fun getGpuUpdateTime(): Long

    fun setGpuUpdateTime(time: Long)

    fun getAsicUpdateTime(): Long

    fun setAsicUpdateTime(time: Long)

    fun getAltcoinUpdateTime() : Long

    fun setAltcoinUpdateTime(time : Long)

    fun getCryptocurrencyUpdateTime() : Long

    fun setCryptocurrencyUpdateTime(time: Long)
}