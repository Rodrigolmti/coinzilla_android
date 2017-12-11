package com.rodrigolmti.coinzilla.coinzilla.model.presenter

import android.content.Context
import com.rodrigolmti.coinzilla.coinzilla.model.business.Business
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineAsic
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineGpu
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineWarz
import com.rodrigolmti.coinzilla.library.controller.mvp.BaseBusiness
import com.rodrigolmti.coinzilla.library.controller.mvp.BasePresenter
import com.rodrigolmti.coinzilla.library.controller.mvp.BaseView
import com.rodrigolmti.coinzilla.library.util.Action

class Presenter(val view: BaseView, val context: Context) : BasePresenter {

    val business: BaseBusiness = Business(this)

    override fun showProgressBar(visibility: Int) {
        view.showProgressBar(visibility)
    }

    override fun getToken() {
        business.getToken()
    }

    override fun whatToMineGpuWeb() {
        business.whatToMineGpuWeb()
    }

    override fun whatToMineAsicWeb() {
        business.whatToMineAsicWeb()
    }

    override fun whatToMineWarzWeb() {
        business.whatToMineWarzWeb()
    }

    override fun exchangesWeb(fsym: String, tsym: String) {
        business.exchangesWeb(fsym, tsym)
    }

    override fun historicWeb(fsym: String, tsym: String) {
        business.historicWeb(fsym, tsym)
    }

    override fun cryptoCurrencyWeb() {
        business.cryptoCurrencyWeb()
    }

    override fun poloniexBalance() {
        business.poloniexBalance()
    }

    override fun whatToMineGpuLocal(): List<WhatToMineGpu> {
        return business.whatToMineGpuLocal()
    }

    override fun whatToMineAsicLocal(): List<WhatToMineAsic> {
        return business.whatToMineAsicLocal()
    }

    override fun whatToMineWarzLocal(): List<WhatToMineWarz> {
        return business.whatToMineWarzLocal()
    }

    override fun cryptoCurrencyLocal(): List<CryptoCurrency> {
        return business.cryptoCurrencyLocal()
    }

    override fun updateCryptoCurrencyFavorite(item: CryptoCurrency) {
        business.updateCryptoCurrencyFavorite(item)
    }

    override fun getAllFavorites(): List<CryptoCurrency> {
        return business.getAllFavorites()
    }

    override fun success(action: Action) {
        view.success(action)
    }

    override fun success(result: List<Any>) {
        view.success(result)
    }

    override fun error(action: Action) {
        view.error(action)
    }

    override fun error(message: String) {
        view.error(message)
    }

    override fun context(): Context {
        return context
    }
}