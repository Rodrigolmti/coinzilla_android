package com.rodrigolmti.coinzilla.coinzilla.model.api.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitService {

    fun retrofitInstance(baseUrl: String): Retrofit {

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(LoggingInterceptor())

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .build()
    }

    internal class LoggingInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {

            println("## LOG INTERCEPTOR REQUEST ##")
            val request = chain.request()
            println("|------>> Request: " + request.url())

            println("## LOG INTERCEPTOR RESPONSE ##")
            val response = chain.proceed(request)
            val body = response.body()!!.string()
            println("<<------| Response: " + body)

            return response.newBuilder().body(ResponseBody.create(response.body()!!.contentType(), body)).build()
        }
    }
}
