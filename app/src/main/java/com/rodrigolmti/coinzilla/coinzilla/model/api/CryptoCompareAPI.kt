package com.rodrigolmti.coinzilla.coinzilla.model.api

import com.rodrigolmti.coinzilla.coinzilla.model.dto.BaseExchangeDTO
import com.rodrigolmti.coinzilla.coinzilla.model.dto.BaseHistoricDTO
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface CryptoCompareAPI {

    @GET("coinsnapshot/")
    fun getExchanges(@Query("fsym") fsym: String, @Query("tsym") tsym: String): Observable<BaseExchangeDTO>

    @GET("histoday")
    fun getHistoric(@Query("fsym") fsym: String, @Query("tsym") tsym: String): Observable<BaseHistoricDTO>
}