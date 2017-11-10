package com.rodrigolmti.coinzilla.coinzilla.model.api

import com.google.gson.JsonObject
import com.rodrigolmti.coinzilla.coinzilla.model.dto.BaseDTO
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineWarz
import retrofit2.http.GET
import retrofit2.http.Header
import rx.Observable

interface WhatToMineAPI {

    @GET("getToken")
    fun getToken(@Header("identification") identification: String): Observable<BaseDTO<Any>>

    @GET("coins.json")
    fun getGpu(): Observable<JsonObject>

    @GET("asic.json")
    fun getAsic(): Observable<JsonObject>

    @GET("coin/warZ")
    fun getWarz(@Header("authorization") authorization: String): Observable<BaseDTO<WhatToMineWarz>>
}