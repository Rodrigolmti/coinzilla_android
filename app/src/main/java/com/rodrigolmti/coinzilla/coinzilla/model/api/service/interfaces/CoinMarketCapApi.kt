package com.rodrigolmti.coinzilla.coinzilla.model.api.service.interfaces

import com.rodrigolmti.coinzilla.coinzilla.model.entity.coin.CryptoCurrency
import retrofit2.http.GET
import rx.Observable

interface CoinMarketCapApi {

    @GET("ticker/?convert=BRL")
    fun getCryptoCurrency(): Observable<List<CryptoCurrency>>

    @GET("ticker/?convert=BRL&limit=10")
    fun getTenCryptoCurrency(): Observable<List<CryptoCurrency>>
}