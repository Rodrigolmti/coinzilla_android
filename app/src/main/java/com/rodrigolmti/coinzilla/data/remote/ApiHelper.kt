package com.rodrigolmti.coinzilla.data.remote

import android.content.Context
import com.rodrigolmti.coinzilla.data.local.IPreferencesHelper
import com.rodrigolmti.coinzilla.data.model.api.*
import com.rodrigolmti.coinzilla.data.remote.endpoint.ICryptoCompareApi
import com.rodrigolmti.coinzilla.data.remote.endpoint.INodeApi
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import com.rodrigolmti.coinzilla.util.ApiException
import com.rodrigolmti.coinzilla.util.ConnectionException
import com.rodrigolmti.coinzilla.util.isDeviceOnline
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

@PerApplication
class ApiHelper
@Inject constructor(
        private val iPreferencesHelper: IPreferencesHelper,
        private val iCryptoCompareApi: ICryptoCompareApi,
        @AppContext val context: Context,
        private val iNodeApi: INodeApi) : IApiHelper {

    override fun getToken(): Single<AuthenticationResponse> {
        if (!isDeviceOnline(context)) return Single.error(ConnectionException())
        return iNodeApi.getToken(UUID.randomUUID().toString())
    }

    override fun getWhatToMineGpu(): Single<List<WtmGpuResponse>> {
        if (!isDeviceOnline(context)) return Single.error(ConnectionException())
        return iNodeApi.getGpu(iPreferencesHelper.getAuthenticationToken()).flatMap {
            if (it.success && it.data.isNotEmpty()) {
                Single.just(it.data)
            } else {
                Single.error(ApiException(it.message))
            }
        }
    }

    override fun getWhatToMineAsic(): Single<List<WtmAsicResponse>> {
        if (!isDeviceOnline(context)) return Single.error(ConnectionException())
        return iNodeApi.getAsic(iPreferencesHelper.getAuthenticationToken()).flatMap {
            if (it.success && it.data.isNotEmpty()) {
                Single.just(it.data)
            } else {
                Single.error(ApiException(it.message))
            }
        }
    }

    override fun getWhatToMineAltcoins(): Single<List<WtmAltcoinResponse>> {
        if (!isDeviceOnline(context)) return Single.error(ConnectionException())
        return iNodeApi.getAltcoins(iPreferencesHelper.getAuthenticationToken()).flatMap {
            if (it.success && it.data.isNotEmpty()) {
                Single.just(it.data)
            } else {
                Single.error(ApiException(it.message))
            }
        }
    }

    override fun getMarketCapCoinDetail(id: String): Single<CryptoCurrencyResponse> {
        if (!isDeviceOnline(context)) return Single.error(ConnectionException())
        return iNodeApi.getMarketCapCoinDetail(iPreferencesHelper.getAuthenticationToken(), id).flatMap {
            if (it.success) {
                Single.just(it.data)
            } else {
                Single.error(ApiException(it.message))
            }
        }
    }

    override fun getMarketCapList(): Single<List<CryptoCurrencyResponse>> {
        if (!isDeviceOnline(context)) return Single.error(ConnectionException())
        return iNodeApi.getMarketCapList(iPreferencesHelper.getAuthenticationToken()).flatMap {
            if (it.success && it.data.isNotEmpty()) {
                Single.just(it.data)
            } else {
                Single.error(ApiException(it.message))
            }
        }
    }

    override fun getExchanges(fsym: String, tsym: String): Single<List<ExchangeResponse>> {
        if (!isDeviceOnline(context)) return Single.error(ConnectionException())
        return iCryptoCompareApi.getExchanges(fsym, tsym).flatMap {
            if (it.success && it.data.isNotEmpty()) {
                Single.just(it.data)
            } else {
                Single.error(ApiException(it.message))
            }
        }
    }

    override fun getHistoric(fsym: String, tsym: String): Single<List<HistoricResponse>> {
        if (!isDeviceOnline(context)) return Single.error(ConnectionException())
        return iCryptoCompareApi.getHistoric(fsym, tsym).flatMap {
            if (it.success && it.data.isNotEmpty()) {
                Single.just(it.data)
            } else {
                Single.error(ApiException(it.message))
            }
        }
    }
}