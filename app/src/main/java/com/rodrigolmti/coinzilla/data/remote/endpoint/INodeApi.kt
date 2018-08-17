package com.rodrigolmti.coinzilla.data.remote.endpoint

import com.rodrigolmti.coinzilla.data.model.api.AuthenticationResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmAltcoinListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface INodeApi {

    @GET("getToken")
    fun getToken(@Header("identification") identification: String): Single<AuthenticationResponse>

    @GET("coin/warZ")
    fun getAltcoins(@Header("authorization") authorization: String): Single<WtmAltcoinListResponse>
}