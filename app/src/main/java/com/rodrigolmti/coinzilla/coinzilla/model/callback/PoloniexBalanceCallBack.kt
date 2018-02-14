package com.rodrigolmti.coinzilla.coinzilla.model.callback

import com.rodrigolmti.coinzilla.coinzilla.model.entity.poloniex.PoloniexBalances

/**
 * Created by rodrigolmti on 12/02/18.
 */
open class PoloniexBalanceCallBack : BaseCallBack() {

    open fun onSuccess(data: PoloniexBalances) {}
}