package com.rodrigolmti.coinzilla.library.controller.mvp

import com.rodrigolmti.coinzilla.coinzilla.model.entity.*

interface BaseBusiness {

    fun getToken()
    fun whatToMineGpuWeb()
    fun whatToMineAsicWeb()
    fun whatToMineWarzWeb()
    fun cryptoCurrencyWeb()
    fun tenCryptoCurrencyWeb()
    fun exchangesWeb(fsym: String, tsym: String)
    fun historicWeb(fsym: String, tsym: String)

    fun whatToMineGpuLocal(): List<WhatToMineGpu>
    fun whatToMineAsicLocal(): List<WhatToMineAsic>
    fun whatToMineWarzLocal(): List<WhatToMineWarz>
    fun cryptoCurrencyLocal(): List<CryptoCurrency>

    fun poloniexBalance()

    fun updateCryptoCurrencyFavorite(item: CryptoCurrency)
    fun getAllFavorites(): List<CryptoCurrency>
}