package com.rodrigolmti.coinzilla.data.remote.endpoint

import com.rodrigolmti.coinzilla.data.model.api.CryptoCompareResponse
import com.rodrigolmti.coinzilla.data.model.api.ExchangeResponse
import com.rodrigolmti.coinzilla.data.model.api.HistoricResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ICryptoCompareApi {

    @GET("top/exchanges")
    fun getExchanges(@Query("fsym") fsym: String, @Query("tsym") tsym: String): Single<CryptoCompareResponse<ExchangeResponse>>

    @GET("histoday")
    fun getHistoric(@Query("fsym") fsym: String, @Query("tsym") tsym: String): Single<CryptoCompareResponse<HistoricResponse>>
}