package com.rodrigolmti.coinzilla.data.remote

import com.rodrigolmti.coinzilla.coinzilla.model.dto.BaseExchangeDTO
import com.rodrigolmti.coinzilla.coinzilla.model.dto.HistoricResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ICryptoCompareApi {

    @GET("top/exchanges")
    fun getExchanges(@Query("fsym") fsym: String, @Query("tsym") tsym: String): Single<BaseExchangeDTO>

    @GET("histoday")
    fun getHistoric(@Query("fsym") fsym: String, @Query("tsym") tsym: String): Single<HistoricResponse>
}