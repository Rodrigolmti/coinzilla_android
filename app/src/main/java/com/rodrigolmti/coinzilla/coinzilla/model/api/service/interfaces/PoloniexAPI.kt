package com.rodrigolmti.coinzilla.coinzilla.model.api.service.interfaces

import com.rodrigolmti.coinzilla.coinzilla.model.entity.PoloniexBalances
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

import retrofit2.http.Header
import retrofit2.http.POST
import rx.Observable

interface PoloniexAPI {

    @POST("")
    @FormUrlEncoded
    fun getBalances(
            @Header("Key") key: String,
            @Header("Sign") sign: String,
            @Field("command") command: String,
            @Field("nonce") nonce: String): Observable<PoloniexBalances>
}