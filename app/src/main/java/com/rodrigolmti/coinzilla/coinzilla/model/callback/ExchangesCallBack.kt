package com.rodrigolmti.coinzilla.coinzilla.model.callback

import com.rodrigolmti.coinzilla.coinzilla.model.entity.Exchange

/**
 * Created by rodrigolmti on 12/02/18.
 * At Framework System
 */
open class ExchangesCallBack : BaseCallBack() {

    open fun onSuccess(list: List<Exchange>) {}
}