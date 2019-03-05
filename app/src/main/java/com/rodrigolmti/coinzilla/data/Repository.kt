package com.rodrigolmti.coinzilla.data

import com.rodrigolmti.coinzilla.data.local.IPreferencesHelper
import com.rodrigolmti.coinzilla.data.model.api.*
import com.rodrigolmti.coinzilla.data.remote.IApiHelper
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import com.rodrigolmti.coinzilla.util.TokenValid
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

@PerApplication
class Repository
@Inject constructor(
        private val iApiHelper: IApiHelper,
        private val iPreferencesHelper: IPreferencesHelper) : IRepository {

    override fun getToken(): Single<AuthenticationResponse> {
        if (isCurrentTokenExpired()) {
            return iApiHelper.getToken().flatMap {
                if (it.success && it.token.isNotEmpty()) {
                    setAuthenticationToken(it.token)
                    setAuthenticationTokenTime(Date().time)
                }
                Single.just(it)
            }
        }
        return Single.error(TokenValid())
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
        return iApiHelper.getWhatToMineGpu().flatMap {
            setGpuUpdateTime(Date().time)
            Single.just(it)
        }
    }

    override fun getWhatToMineAsic(): Single<List<WtmAsicResponse>> {
        return iApiHelper.getWhatToMineAsic().flatMap {
            setAsicUpdateTime(Date().time)
            Single.just(it)
        }
    }

    override fun getWhatToMineAltcoins(): Single<List<WtmAltcoinResponse>> {
        return iApiHelper.getWhatToMineAltcoins().flatMap {
            setAltcoinUpdateTime(Date().time)
            Single.just(it)
        }
    }

    override fun getMarketCapList(): Single<List<CryptoCurrencyResponse>> {
        return iApiHelper.getMarketCapList().flatMap {
            setCryptocurrencyUpdateTime(Date().time)
            Single.just(it)
        }
    }

    override fun getMarketCapCoinDetail(id: String): Single<CryptoCurrencyResponse> {
        return iApiHelper.getMarketCapCoinDetail(id)
    }

    override fun getExchanges(fsym: String, tsym: String): Single<List<ExchangeResponse>> {
        return iApiHelper.getExchanges(fsym, tsym)
    }

    override fun getHistoric(fsym: String, tsym: String): Single<List<HistoricResponse>> {
        return iApiHelper.getHistoric(fsym, tsym)
    }

    private fun isCurrentTokenExpired(): Boolean {
        if (getAuthenticationTokenTime() == 0L) {
            return true
        }

        val calendar: Calendar = Calendar.getInstance()
        calendar.time = Date(getAuthenticationTokenTime())
        calendar.add(Calendar.HOUR_OF_DAY, +20)
        val dateAfter: Date = calendar.time
        return Date().after(dateAfter)
    }
}