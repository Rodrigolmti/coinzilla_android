package com.rodrigolmti.coinzilla.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rodrigolmti.coinzilla.BuildConfig
import com.rodrigolmti.coinzilla.data.remote.endpoint.ICryptoCompareApi
import com.rodrigolmti.coinzilla.data.remote.endpoint.IMakertCapApi
import com.rodrigolmti.coinzilla.data.remote.endpoint.IWhatToMineApi
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule {

    companion object {

        const val BASE_URL_WHAT_TO_MINE = "https://whattomine.com/"
        const val BASE_URL_MARKET_CAP = "https://api.coinmarketcap.com/v1/"
        const val BASE_URL_CRYPTO_COMPARE = "https://min-api.cryptocompare.com/data/"

    }

    //TODO: Refactor

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
    internal fun provideRemoteWhatToMineApi(gson: Gson, okHttpClient: OkHttpClient): IWhatToMineApi {
        val httpClientBuilder = setupHttpBuilder(okHttpClient)

        return Retrofit.Builder()
                .baseUrl(BASE_URL_WHAT_TO_MINE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(IWhatToMineApi::class.java)
    }

    @Provides
    @PerApplication
    internal fun provideRemoteMarketCapApi(gson: Gson, okHttpClient: OkHttpClient): IMakertCapApi {
        val httpClientBuilder = setupHttpBuilder(okHttpClient)

        return Retrofit.Builder()
                .baseUrl(BASE_URL_MARKET_CAP)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(IMakertCapApi::class.java)
    }

    @Provides
    @PerApplication
    internal fun provideRemoteCryptoCompareApi(gson: Gson, okHttpClient: OkHttpClient): ICryptoCompareApi {
        val httpClientBuilder = setupHttpBuilder(okHttpClient)

        return Retrofit.Builder()
                .baseUrl(BASE_URL_CRYPTO_COMPARE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(ICryptoCompareApi::class.java)
    }

    private fun setupHttpBuilder(okHttpClient: OkHttpClient): OkHttpClient.Builder {
        val httpClientBuilder = okHttpClient.newBuilder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return httpClientBuilder
    }
}
