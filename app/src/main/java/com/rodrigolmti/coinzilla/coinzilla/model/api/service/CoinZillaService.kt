package com.rodrigolmti.coinzilla.coinzilla.model.api.service

import android.content.Context
import com.rodrigolmti.coinzilla.coinzilla.model.callback.ExchangesCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.callback.HistoricCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.callback.PoloniexBalanceCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.dto.ExchangesResponse

class CoinZillaService(private val context: Context) {

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
}
