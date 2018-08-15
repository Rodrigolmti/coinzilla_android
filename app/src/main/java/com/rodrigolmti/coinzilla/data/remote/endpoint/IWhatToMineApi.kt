package com.rodrigolmti.coinzilla.data.remote.endpoint

import com.google.gson.JsonObject
import com.rodrigolmti.coinzilla.coinzilla.model.dto.BaseListDTO
import com.rodrigolmti.coinzilla.coinzilla.model.entity.coin.WhatToMineWarz
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface IWhatToMineApi {

    @GET("coins.json")
    fun getGpu(): Single<JsonObject>

    @GET("asic.json")
    fun getAsic(): Single<JsonObject>

    @GET("coin/warZ")
    fun getWarz(@Header("authorization") authorization: String): Single<BaseListDTO<WhatToMineWarz>>
}