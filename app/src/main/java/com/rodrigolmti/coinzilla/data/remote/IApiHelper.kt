package com.rodrigolmti.coinzilla.data.remote

import com.rodrigolmti.coinzilla.data.model.api.WtmAsicResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmGpuResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmWarzResponse
import io.reactivex.Single

interface IApiHelper {

    fun getToken()

    fun getWhatToMineGpu(): Single<List<WtmGpuResponse>>

    fun getWhatToMineAsic(): Single<List<WtmAsicResponse>>

    fun getWhatToMineWarz(): Single<List<WtmWarzResponse>>

    fun getCryptoCurrency()
}