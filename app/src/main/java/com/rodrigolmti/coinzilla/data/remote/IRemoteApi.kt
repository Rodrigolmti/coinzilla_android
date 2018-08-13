package com.rodrigolmti.coinzilla.data.remote

import com.rodrigolmti.coinzilla.coinzilla.model.dto.BaseListDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface IRemoteApi {

    @GET("getToken")
    fun getToken(@Header("identification") identification : String) : Single<BaseListDTO<Any>>
}