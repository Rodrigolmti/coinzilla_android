package com.rodrigolmti.coinzilla.data.remote

import com.rodrigolmti.coinzilla.data.model.api.*
import io.reactivex.Single

interface IApiHelper {

    fun getToken(): Single<AuthenticationResponse>

    fun getWhatToMineGpu(): Single<List<WtmGpuResponse>>

    fun getWhatToMineAsic(): Single<List<WtmAsicResponse>>

    fun getWhatToMineAltcoins(): Single<List<WtmAltcoinResponse>>

    fun getCryptoCurrency(): Single<List<CryptoCurrencyResponse>>
}