package com.rodrigolmti.coinzilla.coinzilla.model.api.service.interfaces

import com.rodrigolmti.coinzilla.coinzilla.model.dto.BaseExchangeDTO
import com.rodrigolmti.coinzilla.coinzilla.model.dto.BaseHistoricDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCompareAPI {

    @GET("top/exchanges")
    fun getExchanges(@Query("fsym") fsym: String, @Query("tsym") tsym: String): Observable<BaseExchangeDTO>

    @GET("histoday")
    fun getHistoric(@Query("fsym") fsym: String, @Query("tsym") tsym: String): Observable<BaseHistoricDTO>
}