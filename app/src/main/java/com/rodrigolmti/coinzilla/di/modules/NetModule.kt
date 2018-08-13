package com.rodrigolmti.coinzilla.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.rodrigolmti.coinzilla.BuildConfig
import com.rodrigolmti.coinzilla.data.remote.IRemoteApi
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import io.realm.RealmList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule {

    @Provides
    @PerApplication
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @PerApplication
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @PerApplication
    internal fun provideRemoteApi(gson: Gson, okHttpClient: OkHttpClient): IRemoteApi {
        val httpClientBuilder = okHttpClient.newBuilder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
                .baseUrl("http://67.205.185.235:3000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(IRemoteApi::class.java)
    }
}
