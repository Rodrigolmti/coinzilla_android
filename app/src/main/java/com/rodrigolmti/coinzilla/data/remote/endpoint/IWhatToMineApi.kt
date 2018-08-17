package com.rodrigolmti.coinzilla.data.remote.endpoint

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET

interface IWhatToMineApi {

    @GET("coins.json")
    fun getGpu(): Single<JsonObject>

    @GET("asic.json")
    fun getAsic(): Single<JsonObject>
}