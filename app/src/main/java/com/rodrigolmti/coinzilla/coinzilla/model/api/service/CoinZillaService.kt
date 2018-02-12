package com.rodrigolmti.coinzilla.coinzilla.model.api.service

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.api.service.interfaces.CoinMarketCapApi
import com.rodrigolmti.coinzilla.coinzilla.model.api.service.interfaces.CryptoCompareAPI
import com.rodrigolmti.coinzilla.coinzilla.model.api.service.interfaces.WhatToMineAPI
import com.rodrigolmti.coinzilla.coinzilla.model.callback.BaseCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.callback.ExchangesCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.callback.HistoricCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.dao.CoinDAO
import com.rodrigolmti.coinzilla.coinzilla.model.dao.Preferences
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineAsic
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineGpu
import com.rodrigolmti.coinzilla.library.app.CZApplication
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.library.util.Utils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.UUID

class CoinZillaService(private val context: Context) {

    private val czPreferences: Preferences? = CZApplication.preferences
    private val coinDao: CoinDAO = CoinDAO()
    private val utils: Utils = Utils()

    fun getToken(callback: BaseCallBack) {
        try {
            if (czPreferences!!.identification == "noData")
                czPreferences.identification = UUID.randomUUID().toString()

            RetrofitService().retrofitInstance(context.getString(R.string.base_url_main)).create(WhatToMineAPI::class.java).getToken(czPreferences.identification)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        if (data.success) {
                            czPreferences.token = data.token
                            czPreferences.tokenDate = utils.formatDate(Date())
                            callback.onSuccess()
                        } else {
                            callback.onError(context.getString(R.string.general_error_connection))
                        }
                    }, { error ->
                        handleError(callback, error)
                    })
        } catch (error: Exception) {
            handleError(callback, error)
        }
    }

    fun getWhatToMineGpu(callback: BaseCallBack) {
        try {
            if (checkTime(Action.GPU, 15) || coinDao.getAllWhatToMineGpu().isEmpty()) {
                RetrofitService().retrofitInstance(context.getString(R.string.base_url_what_to_mine)).create(WhatToMineAPI::class.java).getGpu()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ data ->
                            val json = data.entrySet()
                            val gson = Gson()

                            val baseCoin = json.elementAt(0)
                            val coinHash = baseCoin.value
                            val coins = (coinHash as JsonObject).entrySet()
                            val gpus: ArrayList<WhatToMineGpu> = coins.mapTo(ArrayList()) {
                                gson.fromJson(coinHash.get(it.key), WhatToMineGpu::class.java)
                            }
                            if (gpus.isNotEmpty()) {
                                czPreferences!!.updateDateGpu = utils.formatDate(Date())
                                coinDao.insertWhatToMineGpu(gpus)
                                callback.onSuccess()
                            } else {
                                callback.onError(context.getString(R.string.general_error_connection))
                            }
                        }, { error ->
                            if (coinDao.getAllWhatToMineGpu().isNotEmpty()) {
                                callback.onSuccess()
                            } else {
                                handleError(callback, error)
                            }
                        })
            } else {
                callback.onSuccess()
            }
        } catch (error: Exception) {
            handleError(callback, error)
        }
    }

    fun getWhatToMineAsic(callback: BaseCallBack) {
        try {
            if (checkTime(Action.ASIC, 15) || coinDao.getAllWhatToMineAsic().isEmpty()) {
                RetrofitService().retrofitInstance(context.getString(R.string.base_url_what_to_mine)).create(WhatToMineAPI::class.java).getAsic()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ data ->
                            val json = data.entrySet()
                            val gson = Gson()

                            val baseCoin = json.elementAt(0)
                            val coinHash = baseCoin.value
                            val coins = (coinHash as JsonObject).entrySet()
                            val asics: ArrayList<WhatToMineAsic> = coins.mapTo(ArrayList()) {
                                gson.fromJson(coinHash.get(it.key), WhatToMineAsic::class.java)
                            }
                            if (asics.isNotEmpty()) {
                                czPreferences!!.updateDateAsic = utils.formatDate(Date())
                                coinDao.insertWhatToMineAsic(asics)
                                callback.onSuccess()
                            } else {
                                callback.onError(context.getString(R.string.general_error_connection))
                            }
                        }, { error ->
                            if (coinDao.getAllWhatToMineAsic().isNotEmpty()) {
                                callback.onSuccess()
                            } else {
                                handleError(callback, error)
                            }
                        })
            } else {
                callback.onSuccess()
            }
        } catch (error: Exception) {
            handleError(callback, error)
        }
    }

    fun getWhatToMineWarz(callback: BaseCallBack) {
        try {
            if (checkTime(Action.WARZ, 15) || coinDao.getAllWhatToMineWarz().isEmpty()) {
                RetrofitService().retrofitInstance(context.getString(R.string.base_url_main)).create(WhatToMineAPI::class.java).getWarz(czPreferences!!.token)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ data ->
                            if (data.success) {
                                czPreferences.updateDateWarz = utils.formatDate(Date())
                                coinDao.insertWhatToMineWarz(data.data)
                                callback.onSuccess()
                            } else {
                                callback.onError(context.getString(R.string.general_error_connection))
                            }
                        }, { error ->
                            if (coinDao.getAllWhatToMineWarz().isNotEmpty()) {
                                callback.onSuccess()
                            } else {
                                handleError(callback, error)
                            }
                        })
            } else {
                callback.onSuccess()
            }
        } catch (error: Exception) {
            handleError(callback, error)
        }
    }

    fun getExchanges(callback: ExchangesCallBack, fsym: String, tsym: String) {
        try {
            RetrofitService().retrofitInstance(context.getString(R.string.base_url_crypto_compare_2)).create(CryptoCompareAPI::class.java).getExchanges(fsym, tsym)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        if (data.succes == "Success") {
                            callback.onSuccess(data.exchangeCoin)
                        } else {
                            callback.onError()
                        }
                    }, { error ->
                        if (coinDao.getAllWhatToMineWarz().isNotEmpty()) {
                            callback.onSuccess()
                        } else {
                            handleError(callback, error)
                        }
                    })
        } catch (error: Exception) {
            handleError(callback, error)
        }
    }

    fun getHistoric(callback: HistoricCallBack, fsym: String, tsym: String) {
        try {
            RetrofitService().retrofitInstance(context.getString(R.string.base_url_crypto_compare_2)).create(CryptoCompareAPI::class.java).getHistoric(fsym, tsym)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        if (data.succes == "Success") {
                            callback.onSuccess(data.historic)
                        } else {
                            callback.onError()
                        }
                    }, { error ->
                        if (coinDao.getAllWhatToMineWarz().isNotEmpty()) {
                            callback.onSuccess()
                        } else {
                            handleError(callback, error)
                        }
                    })
        } catch (error: Exception) {
            handleError(callback, error)
        }
    }

    fun getCryptoCurrency(callback: BaseCallBack) {
        try {
            if (checkTime(Action.CRYPTOCURRENCY, 15) || coinDao.getAllCryptoCurrency().isEmpty()) {
                RetrofitService().retrofitInstance(context.getString(R.string.base_url_market_Cap)).create(CoinMarketCapApi::class.java).getCryptoCurrency()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ data ->
                            czPreferences!!.updateDateCryptoCurrency = utils.formatDate(Date())
                            coinDao.insertCryptoCurrency(data)
                            callback.onSuccess()
                        }, { error ->
                            if (coinDao.getAllCryptoCurrency().isNotEmpty()) {
                                callback.onSuccess()
                            } else {
                                handleError(callback, error)
                            }
                        })
            } else {
                callback.onSuccess()
            }
        } catch (error: Exception) {
            handleError(callback, error)
        }
    }

    fun getTopTenCryptocurrency(callback: BaseCallBack) {
        try {
            RetrofitService().retrofitInstance(context.getString(R.string.base_url_market_Cap)).create(CoinMarketCapApi::class.java).getTenCryptoCurrency()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        coinDao.insertCryptoCurrency(data)
                        callback.onSuccess()
                    }, { error ->
                        if (coinDao.getAllCryptoCurrency().isNotEmpty()) {
                            callback.onSuccess()
                        } else {
                            handleError(callback, error)
                        }
                    })
        } catch (error: Exception) {
            handleError(callback, error)
        }
    }

    fun poloniexBalance() {
//        try {
//            presenter.showProgressBar(View.VISIBLE)
//            RetrofitService().retrofitInstance(context.getString(R.string.base_url_poloniex_trading)).create(PoloniexAPI::class.java).getBalances()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        data ->
//                        presenter.showProgressBar(View.GONE)
//                    }, {
//                        error ->
//                        presenter.showProgressBar(View.GONE)
//                        if (getAllCryptoCurrency().isNotEmpty()) {
//                            presenter.success(Action.CRYPTOCURRENCY)
//                        } else {
//                            presenter.error(presenter.context().getString(R.string.general_error_connection))
//                            error.printStackTrace()
//                        }
//                    })
//        } catch (error: Exception) {
//            Crashlytics.logException(error)
//            presenter.showProgressBar(View.GONE)
//            presenter.error(presenter.context().getString(R.string.general_error_connection))
//        }
    }

    private fun handleError(callback: BaseCallBack, error: Any) {
        callback.onError(context.getString(R.string.general_error_connection))
        if (error is Throwable) {
            Crashlytics.logException(error)
            error.printStackTrace()
        }
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
