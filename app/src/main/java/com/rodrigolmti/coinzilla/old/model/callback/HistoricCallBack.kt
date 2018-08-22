package com.rodrigolmti.coinzilla.old.model.callback

import com.rodrigolmti.coinzilla.data.model.api.HistoricResponse

/**
* Created by rodrigolmti on 12/02/18.
*/
open class HistoricCallBack : BaseCallBack() {

    open fun onSuccess(list: List<HistoricResponse>) {}
}