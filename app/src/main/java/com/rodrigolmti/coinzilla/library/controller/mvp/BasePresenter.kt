package com.rodrigolmti.coinzilla.library.controller.mvp

import android.content.Context
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineAsic
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineGpu
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineWarz
import com.rodrigolmti.coinzilla.library.util.Action

interface BasePresenter {

    fun getToken()
    fun whatToMineGpuWeb()
    fun whatToMineAsicWeb()
    fun whatToMineWarzWeb()
    fun cryptoCurrencyWeb()
    fun exchangesWeb(fsym: String, tsym: String)
    fun historicWeb(fsym: String, tsym: String)

    fun showProgressBar(visibility: Int)
    fun success(action: Action)
    fun success(result: List<Any>)
    fun error(action: Action)
    fun error(message: String)
    fun context(): Context

    fun whatToMineGpuLocal(): List<WhatToMineGpu>
    fun whatToMineAsicLocal(): List<WhatToMineAsic>
    fun whatToMineWarzLocal(): List<WhatToMineWarz>
    fun cryptoCurrencyLocal(): List<CryptoCurrency>

    fun poloniexBalance()

    fun updateCryptoCurrencyFavorite(item: CryptoCurrency)
    fun getAllFavorites(): List<CryptoCurrency>
}