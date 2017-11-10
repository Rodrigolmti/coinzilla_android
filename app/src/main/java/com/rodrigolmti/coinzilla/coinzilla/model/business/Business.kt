package com.rodrigolmti.coinzilla.coinzilla.model.business

import android.content.Context
import android.view.View
import com.crashlytics.android.Crashlytics
import com.google.gson.Gson
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.api.CoinMarketCapApi
import com.rodrigolmti.coinzilla.coinzilla.model.api.CryptoCompareAPI
import com.rodrigolmti.coinzilla.coinzilla.model.api.PoloniexAPI
import com.rodrigolmti.coinzilla.coinzilla.model.api.WhatToMineAPI
import com.rodrigolmti.coinzilla.coinzilla.model.api.service.RetrofitService
import com.rodrigolmti.coinzilla.coinzilla.model.dao.Database
import com.rodrigolmti.coinzilla.coinzilla.model.dao.Preferences
import com.rodrigolmti.coinzilla.coinzilla.model.entity.*
import com.rodrigolmti.coinzilla.library.app.CZApplication
import com.rodrigolmti.coinzilla.library.controller.mvp.BaseBusiness
import com.rodrigolmti.coinzilla.library.controller.mvp.BasePresenter
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.library.util.Utils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import com.google.gson.JsonObject

class Business(private val presenter: BasePresenter) : BaseBusiness {

    private val czPreferences: Preferences? = CZApplication.preferences
    private val context: Context = presenter.context()
    private val database: Database = Database()
    private val utils: Utils = Utils()

    override fun getToken() {
        try {
            presenter.showProgressBar(View.VISIBLE)

            if (czPreferences!!.identification == "noData")
                czPreferences.identification = UUID.randomUUID().toString()

            RetrofitService().retrofitInstance(context.getString(R.string.base_url_main)).create(WhatToMineAPI::class.java).getToken(czPreferences.identification)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        presenter.showProgressBar(View.GONE)
                        if (data.success) {
                            czPreferences.token = data.token
                            czPreferences.tokenDate = utils.formatDate(Date())
                            presenter.success(Action.TOKEN)
                        } else {
                            presenter.error(presenter.context().getString(R.string.general_error_connection))
                        }
                    }, { error ->
                        presenter.showProgressBar(View.GONE)
                        presenter.error(presenter.context().getString(R.string.general_error_connection))
                        error.printStackTrace()
                    })
        } catch (error: Exception) {
            Crashlytics.logException(error)
            presenter.showProgressBar(View.GONE)
            presenter.error(presenter.context().getString(R.string.general_error_connection))
        }
    }

    override fun whatToMineGpuWeb() {
        try {
            if (checkTime(Action.GPU, 15) || whatToMineGpuLocal().isEmpty()) {
                presenter.showProgressBar(View.VISIBLE)
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
                                database.insertWhatToMineGpu(gpus)
                                presenter.showProgressBar(View.GONE)
                                presenter.success(Action.GPU)
                            } else {
                                presenter.showProgressBar(View.GONE)
                                presenter.error(presenter.context().getString(R.string.general_error_connection))
                            }
                        }, { error ->
                            presenter.showProgressBar(View.GONE)
                            if (whatToMineGpuLocal().isNotEmpty()) {
                                presenter.success(Action.GPU)
                            } else {
                                presenter.error(presenter.context().getString(R.string.general_error_connection))
                                error.printStackTrace()
                            }
                        })
            } else {
                presenter.success(Action.GPU)
            }
        } catch (error: Exception) {
            Crashlytics.logException(error)
            presenter.showProgressBar(View.GONE)
            presenter.error(presenter.context().getString(R.string.general_error_connection))
        }
    }

    override fun whatToMineAsicWeb() {
        try {
            if (checkTime(Action.ASIC, 15) || whatToMineAsicLocal().isEmpty()) {
                presenter.showProgressBar(View.VISIBLE)
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
                                database.insertWhatToMineAsic(asics)
                                presenter.showProgressBar(View.GONE)
                                presenter.success(Action.ASIC)
                            } else {
                                presenter.showProgressBar(View.GONE)
                                presenter.error(presenter.context().getString(R.string.general_error_connection))
                            }
                        }, { error ->
                            presenter.showProgressBar(View.GONE)
                            if (whatToMineAsicLocal().isNotEmpty()) {
                                presenter.success(Action.ASIC)
                            } else {
                                presenter.error(presenter.context().getString(R.string.general_error_connection))
                                error.printStackTrace()
                            }
                        })
            } else {
                presenter.success(Action.ASIC)
            }
        } catch (error: Exception) {
            Crashlytics.logException(error)
            presenter.showProgressBar(View.GONE)
            presenter.error(presenter.context().getString(R.string.general_error_connection))
        }
    }

    override fun whatToMineWarzWeb() {
        try {
            if (checkTime(Action.WARZ, 15) || whatToMineWarzLocal().isEmpty()) {
                presenter.showProgressBar(View.VISIBLE)
                RetrofitService().retrofitInstance(context.getString(R.string.base_url_main)).create(WhatToMineAPI::class.java).getWarz(czPreferences!!.token)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ data ->
                            presenter.showProgressBar(View.GONE)
                            if (data.success) {
                                czPreferences.updateDateWarz = utils.formatDate(Date())
                                database.insertWhatToMineWarz(data.data)
                                presenter.success(Action.WARZ)
                            } else {
                                presenter.error(presenter.context().getString(R.string.general_error_connection))
                            }
                        }, { error ->
                            presenter.showProgressBar(View.GONE)
                            if (whatToMineWarzLocal().isNotEmpty()) {
                                presenter.success(Action.WARZ)
                            } else {
                                presenter.error(presenter.context().getString(R.string.general_error_connection))
                                error.printStackTrace()
                            }
                        })
            } else {
                presenter.success(Action.WARZ)
            }
        } catch (error: Exception) {
            Crashlytics.logException(error)
            presenter.showProgressBar(View.GONE)
            presenter.error(presenter.context().getString(R.string.general_error_connection))
        }
    }

    override fun exchangesWeb(fsym: String, tsym: String) {
        try {
            presenter.showProgressBar(View.VISIBLE)
            RetrofitService().retrofitInstance(context.getString(R.string.base_url_crypto_compare)).create(CryptoCompareAPI::class.java).getExchanges(fsym, tsym)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        if (data.succes == "Success") {
                            presenter.success(data.exchangeCoin.exchanges)
                        } else {
                            presenter.error(Action.EXCHANGE)
                        }
                    }, { error ->
                        presenter.showProgressBar(View.GONE)
                        if (whatToMineWarzLocal().isNotEmpty()) {
                            presenter.success(Action.WARZ)
                        } else {
                            presenter.error(presenter.context().getString(R.string.general_error_connection))
                            error.printStackTrace()
                        }
                    })
        } catch (error: Exception) {
            Crashlytics.logException(error)
            presenter.showProgressBar(View.GONE)
            presenter.error(presenter.context().getString(R.string.general_error_connection))
        }
    }

    override fun historicWeb(fsym: String, tsym: String) {
        try {
            presenter.showProgressBar(View.VISIBLE)
            RetrofitService().retrofitInstance(context.getString(R.string.base_url_crypto_compare_2)).create(CryptoCompareAPI::class.java).getHistoric(fsym, tsym)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        if (data.succes == "Success") {
                            presenter.success(data.historic)
                        } else {
                            presenter.error(Action.HISTORIC)
                        }
                    }, { error ->
                        presenter.showProgressBar(View.GONE)
                        if (whatToMineWarzLocal().isNotEmpty()) {
                            presenter.success(Action.WARZ)
                        } else {
                            presenter.error(presenter.context().getString(R.string.general_error_connection))
                            error.printStackTrace()
                        }
                    })
        } catch (error: Exception) {
            Crashlytics.logException(error)
            presenter.showProgressBar(View.GONE)
            presenter.error(presenter.context().getString(R.string.general_error_connection))
        }
    }

    override fun cryptoCurrencyWeb() {
        try {
            if (checkTime(Action.CRYPTOCURRENCY, 15) || cryptoCurrencyLocal().isEmpty()) {
                presenter.showProgressBar(View.VISIBLE)
                RetrofitService().retrofitInstance(context.getString(R.string.base_url_market_Cap)).create(CoinMarketCapApi::class.java).getCryptoCurrency()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ data ->
                            czPreferences!!.updateDateCryptoCurrency = utils.formatDate(Date())
                            database.insertCryptoCurrency(data)
                            presenter.success(Action.CRYPTOCURRENCY)
                            presenter.showProgressBar(View.GONE)
                        }, { error ->
                            presenter.showProgressBar(View.GONE)
                            if (cryptoCurrencyLocal().isNotEmpty()) {
                                presenter.success(Action.CRYPTOCURRENCY)
                            } else {
                                presenter.error(presenter.context().getString(R.string.general_error_connection))
                                error.printStackTrace()
                            }
                        })
            } else {
                presenter.success(Action.CRYPTOCURRENCY)
            }
        } catch (error: Exception) {
            Crashlytics.logException(error)
            presenter.showProgressBar(View.GONE)
            presenter.error(presenter.context().getString(R.string.general_error_connection))
        }
    }

    override fun tenCryptoCurrencyWeb() {
        try {
            presenter.showProgressBar(View.VISIBLE)
            RetrofitService().retrofitInstance(context.getString(R.string.base_url_market_Cap)).create(CoinMarketCapApi::class.java).getTenCryptoCurrency()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        database.insertCryptoCurrency(data)
                        presenter.success(Action.CRYPTOCURRENCY)
                        presenter.showProgressBar(View.GONE)
                    }, { error ->
                        presenter.showProgressBar(View.GONE)
                        if (cryptoCurrencyLocal().isNotEmpty()) {
                            presenter.success(Action.CRYPTOCURRENCY)
                        } else {
                            presenter.error(presenter.context().getString(R.string.general_error_connection))
                            error.printStackTrace()
                        }
                    })
        } catch (error: Exception) {
            Crashlytics.logException(error)
            presenter.showProgressBar(View.GONE)
            presenter.error(presenter.context().getString(R.string.general_error_connection))
        }
    }

    override fun poloniexBalance() {
        try {
            presenter.showProgressBar(View.VISIBLE)
//            RetrofitService().retrofitInstance(context.getString(R.string.base_url_poloniex_trading)).create(PoloniexAPI::class.java).getBalances()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        data ->
//                        presenter.showProgressBar(View.GONE)
//                    }, {
//                        error ->
//                        presenter.showProgressBar(View.GONE)
//                        if (cryptoCurrencyLocal().isNotEmpty()) {
//                            presenter.success(Action.CRYPTOCURRENCY)
//                        } else {
//                            presenter.error(presenter.context().getString(R.string.general_error_connection))
//                            error.printStackTrace()
//                        }
//                    })
        } catch (error: Exception) {
            Crashlytics.logException(error)
            presenter.showProgressBar(View.GONE)
            presenter.error(presenter.context().getString(R.string.general_error_connection))
        }
    }

    override fun updateCryptoCurrencyFavorite(item: CryptoCurrency) {
        database.updateCryptoCurrencyFavorite(item)
    }

    override fun getAllFavorites(): List<CryptoCurrency> {
        return database.getAllFavorites()
    }

    override fun whatToMineGpuLocal(): List<WhatToMineGpu> {
        return database.getAllWhatToMineGpu()
    }

    override fun whatToMineAsicLocal(): List<WhatToMineAsic> {
        return database.getAllWhatToMineAsic()
    }

    override fun whatToMineWarzLocal(): List<WhatToMineWarz> {
        return database.getAllWhatToMineWarz()
    }

    override fun cryptoCurrencyLocal(): List<CryptoCurrency> {
        return database.getAllCryptoCurrency()
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
