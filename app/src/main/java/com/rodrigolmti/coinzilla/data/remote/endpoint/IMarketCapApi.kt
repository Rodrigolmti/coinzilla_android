package com.rodrigolmti.coinzilla.data.remote.endpoint

import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET

interface IMarketCapApi {

    @GET("ticker/?convert=BRL")
    fun getCryptoCurrency(): Single<List<CryptoCurrencyResponse>>
}