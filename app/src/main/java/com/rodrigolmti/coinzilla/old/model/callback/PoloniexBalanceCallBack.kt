package com.rodrigolmti.coinzilla.old.model.callback

import com.rodrigolmti.coinzilla.old.model.entity.poloniex.PoloniexBalances

/**
 * Created by rodrigolmti on 12/02/18.
 */
open class PoloniexBalanceCallBack : BaseCallBack() {

    open fun onSuccess(data: PoloniexBalances) {}
}