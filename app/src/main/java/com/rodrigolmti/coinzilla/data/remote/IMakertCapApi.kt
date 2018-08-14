package com.rodrigolmti.coinzilla.data.remote

import com.rodrigolmti.coinzilla.coinzilla.model.entity.coin.CryptoCurrency
import io.reactivex.Single
import retrofit2.http.GET

interface IMakertCapApi {

    @GET("ticker/?convert=BRL")
    fun getCryptoCurrency(): Single<List<CryptoCurrency>>

    @GET("ticker/?convert=BRL&limit=10")
    fun getTenCryptoCurrency(): Single<List<CryptoCurrency>>
}