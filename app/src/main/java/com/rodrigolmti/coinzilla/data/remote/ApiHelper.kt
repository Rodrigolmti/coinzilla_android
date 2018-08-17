package com.rodrigolmti.coinzilla.data.remote

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.rodrigolmti.coinzilla.data.local.prefs.IPreferencesHelper
import com.rodrigolmti.coinzilla.data.model.api.*
import com.rodrigolmti.coinzilla.data.remote.endpoint.ICryptoCompareApi
import com.rodrigolmti.coinzilla.data.remote.endpoint.IMarketCapApi
import com.rodrigolmti.coinzilla.data.remote.endpoint.INodeApi
import com.rodrigolmti.coinzilla.data.remote.endpoint.IWhatToMineApi
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

@PerApplication
class ApiHelper
@Inject constructor(
        private val iPreferencesHelper: IPreferencesHelper,
        private val iCryptoCompareApi: ICryptoCompareApi,
        private val iWhatToMineApi: IWhatToMineApi,
        private val iMarketCapApi: IMarketCapApi,
        private val iNodeApi: INodeApi) : IApiHelper {

    override fun getToken(): Single<AuthenticationResponse> {
        return iNodeApi.getToken(UUID.randomUUID().toString())
    }

    override fun getWhatToMineGpu(): Single<List<WtmGpuResponse>> {
        return iWhatToMineApi.getGpu().flatMap { it ->
            Single.just(mapJsonToArrayList<WtmGpuResponse>(it))
        }
    }

    override fun getWhatToMineAsic(): Single<List<WtmAsicResponse>> {
        return iWhatToMineApi.getAsic().flatMap { it ->
            Single.just(mapJsonToArrayList<WtmAsicResponse>(it))
        }
    }

    override fun getWhatToMineAltcoins(): Single<List<WtmAltcoinResponse>> {
        return iNodeApi.getAltcoins(iPreferencesHelper.getAuthenticationToken()).flatMap { it ->
            Single.just(it.data)
        }
    }

    override fun getCryptoCurrency(): Single<List<CryptoCurrencyResponse>> {
        return iMarketCapApi.getCryptoCurrency()
    }

    private inline fun <reified T> mapJsonToArrayList(jsonObject: JsonObject): ArrayList<T> {
        val json = jsonObject.entrySet()
        val gson = Gson()
        val baseCoin = json.elementAt(0)
        val coinHash = baseCoin.value
        val coins = (coinHash as JsonObject).entrySet()
        val gpus: ArrayList<T> = coins.mapTo(ArrayList()) {
            gson.fromJson(coinHash.get(it.key), T::class.java)
        }
        return gpus
    }
}