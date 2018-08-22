package com.rodrigolmti.coinzilla.data.remote.endpoint

import com.rodrigolmti.coinzilla.data.model.api.*
import com.rodrigolmti.coinzilla.data.model.api.base.BaseListResponse
import com.rodrigolmti.coinzilla.data.model.api.base.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface INodeApi {

    @GET("getToken")
    fun getToken(@Header("identification") identification: String): Single<AuthenticationResponse>

    @GET("coin/gpu")
    fun getGpu(@Header("authorization") authorization: String): Single<BaseListResponse<WtmGpuResponse>>

    @GET("coin/asic")
    fun getAsic(@Header("authorization") authorization: String): Single<BaseListResponse<WtmAsicResponse>>

    @GET("coin/altcoin")
    fun getAltcoins(@Header("authorization") authorization: String): Single<BaseListResponse<WtmAltcoinResponse>>

    @GET("marketcap/coin/list")
    fun getMarketCapList(@Header("authorization") authorization: String): Single<BaseListResponse<CryptoCurrencyResponse>>

    @GET("marketcap/coin/detail/{id}")
    fun getMarketCapCoinDetail(@Header("authorization") authorization: String, @Path("id") id: String): Single<BaseResponse<CryptoCurrencyResponse>>
}

