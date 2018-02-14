package com.rodrigolmti.coinzilla.coinzilla.model.api.service.interfaces

import com.rodrigolmti.coinzilla.coinzilla.model.dto.BaseDTO
import com.rodrigolmti.coinzilla.coinzilla.model.dto.ExchangeAuthDTO
import com.rodrigolmti.coinzilla.coinzilla.model.entity.poloniex.PoloniexBalances
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

import retrofit2.http.Header
import retrofit2.http.POST
import rx.Observable

interface PoloniexAPI {

    @POST("poloniex/returnAvailableBalances")
    fun getAvailableBalances(
            @Header("authorization") token: String,
            @Body() exchangeAuth: ExchangeAuthDTO): Observable<BaseDTO<PoloniexBalances>>
}