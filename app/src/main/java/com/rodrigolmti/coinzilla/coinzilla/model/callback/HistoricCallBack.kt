package com.rodrigolmti.coinzilla.coinzilla.model.callback

import com.rodrigolmti.coinzilla.coinzilla.model.entity.Historic

/**
* Created by rodrigolmti on 12/02/18.
*/
open class HistoricCallBack : BaseCallBack() {

    open fun onSuccess(list: List<Historic>) {}
}