package com.rodrigolmti.coinzilla.coinzilla.model.api.service.interfaces

import com.google.gson.JsonObject
import com.rodrigolmti.coinzilla.coinzilla.model.dto.BaseListDTO
import com.rodrigolmti.coinzilla.coinzilla.model.entity.coin.WhatToMineWarz
import retrofit2.http.GET
import retrofit2.http.Header
import rx.Observable

interface WhatToMineAPI {

    @GET("getToken")
    fun getToken(@Header("identification") identification: String): Observable<BaseListDTO<Any>>

    @GET("coins.json")
    fun getGpu(): Observable<JsonObject>

    @GET("asic.json")
    fun getAsic(): Observable<JsonObject>

    @GET("coin/warZ")
    fun getWarz(@Header("authorization") authorization: String): Observable<BaseListDTO<WhatToMineWarz>>
}