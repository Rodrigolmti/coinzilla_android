package com.rodrigolmti.coinzilla.coinzilla.model.api.service

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.callback.BaseCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.callback.ExchangesCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.callback.HistoricCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.callback.PoloniexBalanceCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.dao.CoinDAO
import com.rodrigolmti.coinzilla.coinzilla.model.dao.Preferences
import com.rodrigolmti.coinzilla.coinzilla.model.dto.ExchangesResponse
import com.rodrigolmti.coinzilla.CZApplication
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.library.util.Utils
import java.util.Calendar
import java.util.Date

class CoinZillaService(private val context: Context) {

    private val czPreferences: Preferences? = CZApplication.preferences
    private val coinDao: CoinDAO = CoinDAO()
    private val utils: Utils = Utils()

    fun getWhatToMineWarz(callback: BaseCallBack) {
//        try {
//            if (checkTime(Action.WARZ, 15) || coinDao.getAllWhatToMineWarz().isEmpty()) {
//                RetrofitService().retrofitInstance(context.getString(R.string.base_url_main)).create(
//                        WhatToMineAPI::class.java).getWarz(czPreferences!!.token)
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe({ data ->
//                            if (data.success) {
//                                czPreferences.updateDateWarz = utils.formatDate(Date())
//                                coinDao.insertWhatToMineWarz(data.data)
//                                callback.onSuccess()
//                            } else {
//                                callback.onError(context.getString(R.string.general_error_connection))
//                            }
//                        }, { error ->
//                            if (coinDao.getAllWhatToMineWarz().isNotEmpty()) {
//                                callback.onSuccess()
//                            } else {
//                                handleError(callback, error)
//                            }
//                        })
//            } else {
//                callback.onSuccess()
//            }
//        } catch (error: Exception) {
//            handleError(callback, error)
//        }
    }

    fun getExchanges(callback: ExchangesCallBack, fsym: String, tsym: String) {
//        try {
//            RetrofitService().retrofitInstance(context.getString(R.string.base_url_crypto_compare_2)).create(
//                    CryptoCompareAPI::class.java).getExchanges(fsym, tsym)
//                    .subscribeOn(Schedulers.newThread())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ data ->
//                        if (data.succes == "Success") {
//                            callback.onSuccess(data.exchangeCoin)
//                        } else {
//                            callback.onError()
//                        }
//                    }, { error ->
//                        if (coinDao.getAllWhatToMineWarz().isNotEmpty()) {
//                            callback.onSuccess()
//                        } else {
//                            handleError(callback, error)
//                        }
//                    })
//        } catch (error: Exception) {
//            handleError(callback, error)
//        }
    }

    fun getHistoric(callback: HistoricCallBack, fsym: String, tsym: String) {
//        try {
//            RetrofitService().retrofitInstance(context.getString(R.string.base_url_crypto_compare_2)).create(
//                    CryptoCompareAPI::class.java).getHistoric(fsym, tsym)
//                    .subscribeOn(Schedulers.newThread())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ data ->
//                        if (data.succes == "Success") {
//                            callback.onSuccess(data.historic)
//                        } else {
//                            callback.onError()
//                        }
//                    }, { error ->
//                        if (coinDao.getAllWhatToMineWarz().isNotEmpty()) {
//                            callback.onSuccess()
//                        } else {
//                            handleError(callback, error)
//                        }
//                    })
//        } catch (error: Exception) {
//            handleError(callback, error)
//        }
    }

    fun getCryptoCurrency(callback: BaseCallBack) {
//        try {
//            if (checkTime(Action.CRYPTOCURRENCY, 15) || coinDao.getAllCryptoCurrency().isEmpty()) {
//                RetrofitService().retrofitInstance(context.getString(R.string.base_url_market_Cap)).create(
//                        CoinMarketCapApi::class.java).getCryptoCurrency()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe({ data ->
//                            czPreferences!!.updateDateCryptoCurrency = utils.formatDate(Date())
//                            coinDao.insertCryptoCurrency(data)
//                            callback.onSuccess()
//                        }, { error ->
//                            if (coinDao.getAllCryptoCurrency().isNotEmpty()) {
//                                callback.onSuccess()
//                            } else {
//                                handleError(callback, error)
//                            }
//                        })
//            } else {
//                callback.onSuccess()
//            }
//        } catch (error: Exception) {
//            handleError(callback, error)
//        }
    }

    fun getTopTenCryptocurrency(callback: BaseCallBack) {
//        try {
//            RetrofitService().retrofitInstance(context.getString(R.string.base_url_market_Cap)).create(
//                    CoinMarketCapApi::class.java).getTenCryptoCurrency()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ data ->
//                        coinDao.insertCryptoCurrency(data)
//                        callback.onSuccess()
//                    }, { error ->
//                        if (coinDao.getAllCryptoCurrency().isNotEmpty()) {
//                            callback.onSuccess()
//                        } else {
//                            handleError(callback, error)
//                        }
//                    })
//        } catch (error: Exception) {
//            handleError(callback, error)
//        }
    }

    fun getAvailableBalances(callback: PoloniexBalanceCallBack, exchangeAuthDTO: ExchangesResponse) {
//        try {
//            RetrofitService().retrofitInstance(context.getString(R.string.base_url_main)).create(
//                    PoloniexAPI::class.java).getAvailableBalances(czPreferences!!.token, exchangeAuthDTO)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ data ->
//                        callback.onSuccess(data.data)
//                    }, { error ->
//                        handleError(callback, error)
//                    })
//        } catch (error: Exception) {
//            handleError(callback, error)
//        }
    }

    private fun handleError(callback: BaseCallBack, error: Any) {
        callback.onError(context.getString(R.string.general_error_connection))
        if (error is Throwable) {
            Crashlytics.logException(error)
            error.printStackTrace()
        }
        callback.onError()
    }

    private fun checkTime(action: Action, time: Int): Boolean {
        val calendar: Calendar = Calendar.getInstance()
        val stringDate: String = when (action) {
            Action.GPU -> czPreferences!!.updateDateGpu
            Action.ASIC -> czPreferences!!.updateDateAsic
            Action.WARZ -> czPreferences!!.updateDateWarz
            Action.CRYPTOCURRENCY -> czPreferences!!.updateDateCryptoCurrency
            else -> "noData"
        }
        return if (stringDate == "noData") {
            true
        } else {
            val date: Date = utils.stringToDate(stringDate)!!
            calendar.time = date
            calendar.add(Calendar.MINUTE, +time)
            val dateAfter: Date = calendar.time
            Date().after(dateAfter)
        }
    }
}
