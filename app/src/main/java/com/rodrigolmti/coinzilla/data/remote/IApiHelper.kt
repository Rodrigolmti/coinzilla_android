package com.rodrigolmti.coinzilla.data.remote

import com.rodrigolmti.coinzilla.data.model.api.*
import io.reactivex.Single

interface IApiHelper {

    fun getToken(): Single<AuthenticationResponse>

    fun getWhatToMineGpu(): Single<List<WtmGpuResponse>>

    fun getWhatToMineAsic(): Single<List<WtmAsicResponse>>

    fun getWhatToMineAltcoins(): Single<List<WtmAltcoinResponse>>

    fun getMarketCapList(): Single<List<CryptoCurrencyResponse>>

    fun getMarketCapCoinDetail(id: String): Single<CryptoCurrencyResponse>

    fun getExchanges(fsym: String, tsym: String): Single<List<ExchangeResponse>>

    fun getHistoric(fsym: String, tsym: String): Single<List<HistoricResponse>>
}