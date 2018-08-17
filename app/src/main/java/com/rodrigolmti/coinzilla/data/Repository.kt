package com.rodrigolmti.coinzilla.data

import com.rodrigolmti.coinzilla.data.local.db.IDatabaseHelper
import com.rodrigolmti.coinzilla.data.local.prefs.IPreferencesHelper
import com.rodrigolmti.coinzilla.data.model.api.AuthenticationResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmAsicResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmGpuResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmAltcoinResponse
import com.rodrigolmti.coinzilla.data.remote.IApiHelper
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

@PerApplication
class Repository
@Inject constructor(
        private val iApiHelper: IApiHelper,
        private val iDatabaseHelper: IDatabaseHelper,
        private val iPreferencesHelper: IPreferencesHelper) : IRepository {

    override fun getToken(): Single<AuthenticationResponse> {
        if (checkTime()) {
            return iApiHelper.getToken().flatMap { it ->
                if (it.success) {
                    setAuthenticationToken(it.token)
                    setAuthenticationTokenTime(Date().time)
                }
                Single.just(it)
            }
        }
        return Single.just(AuthenticationResponse(true, "", ""))
    }

    override fun getAuthenticationToken(): String {
        return iPreferencesHelper.getAuthenticationToken()
    }

    override fun setAuthenticationToken(token: String) {
        iPreferencesHelper.setAuthenticationToken(token)
    }

    override fun getAuthenticationTokenTime(): Long {
        return iPreferencesHelper.getAuthenticationTokenTime()
    }

    override fun setAuthenticationTokenTime(time: Long) {
        iPreferencesHelper.setAuthenticationTokenTime(time)
    }

    override fun getRealmEncryptionKey(): ByteArray? {
        return iPreferencesHelper.getRealmEncryptionKey()
    }

    override fun setRealmEncryptionKey(bytes: ByteArray?) {
        iPreferencesHelper.setRealmEncryptionKey(bytes)
    }

    override fun getGpuUpdateTime(): Long {
        return iPreferencesHelper.getGpuUpdateTime()
    }

    override fun setGpuUpdateTime(time: Long) {
        iPreferencesHelper.setGpuUpdateTime(time)
    }

    override fun getAsicUpdateTime(): Long {
        return iPreferencesHelper.getAsicUpdateTime()
    }

    override fun setAsicUpdateTime(time: Long) {
        iPreferencesHelper.setAsicUpdateTime(time)
    }

    override fun getAltcoinUpdateTime(): Long {
        return iPreferencesHelper.getAltcoinUpdateTime()
    }

    override fun setAltcoinUpdateTime(time: Long) {
        iPreferencesHelper.setAltcoinUpdateTime(time)
    }

    override fun getCryptocurrencyUpdateTime(): Long {
        return iPreferencesHelper.getCryptocurrencyUpdateTime()
    }

    override fun setCryptocurrencyUpdateTime(time: Long) {
        iPreferencesHelper.setCryptocurrencyUpdateTime(time)
    }

    override fun getWhatToMineGpu(): Single<List<WtmGpuResponse>> {
        return iApiHelper.getWhatToMineGpu().flatMap { it ->
            setGpuUpdateTime(Date().time)
            Single.just(it)
        }
    }

    override fun getWhatToMineAsic(): Single<List<WtmAsicResponse>> {
        return iApiHelper.getWhatToMineAsic().flatMap { it ->
            setAsicUpdateTime(Date().time)
            Single.just(it)
        }
    }

    override fun getWhatToMineAltcoins(): Single<List<WtmAltcoinResponse>> {
        return iApiHelper.getWhatToMineAltcoins().flatMap { it ->
            setAltcoinUpdateTime(Date().time)
            Single.just(it)
        }
    }

    override fun getCryptoCurrency() {
        return iApiHelper.getCryptoCurrency()
    }

    private fun checkTime(): Boolean {
        if (getAuthenticationTokenTime() > 0) {
            val calendar: Calendar = Calendar.getInstance()
            calendar.time = Date(getAuthenticationTokenTime())
            calendar.add(Calendar.HOUR_OF_DAY, +20)
            val dateAfter: Date = calendar.time
            return Date().after(dateAfter)
        }
        return false
    }
}