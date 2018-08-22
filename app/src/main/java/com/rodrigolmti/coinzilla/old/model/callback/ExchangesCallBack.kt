package com.rodrigolmti.coinzilla.old.model.callback

import com.rodrigolmti.coinzilla.data.model.api.ExchangeResponse

/**
* Created by rodrigolmti on 12/02/18.
*/
open class ExchangesCallBack : BaseCallBack() {

    open fun onSuccess(list: List<ExchangeResponse>) {}
}