package com.rodrigolmti.coinzilla.data.remote

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.rodrigolmti.coinzilla.data.model.api.WtmAsicResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmGpuResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmWarzResponse
import com.rodrigolmti.coinzilla.data.remote.endpoint.ICryptoCompareApi
import com.rodrigolmti.coinzilla.data.remote.endpoint.IMakertCapApi
import com.rodrigolmti.coinzilla.data.remote.endpoint.IWhatToMineApi
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

@PerApplication
class ApiHelper
@Inject constructor(
        private val iCryptoCompareApi: ICryptoCompareApi,
        private val iMakertCapApi: IMakertCapApi,
        private val iWhatToMineApi: IWhatToMineApi) : IApiHelper {

    override fun getToken() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override fun getWhatToMineWarz(): Single<List<WtmWarzResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        return iWhatToMineApi.getWarz("").flatMap { it ->
//            Single.just(mapJsonToArrayList<WtmWarzResponse>(it))
//        }
    }

    override fun getCryptoCurrency() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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