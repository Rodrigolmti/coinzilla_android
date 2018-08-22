package com.rodrigolmti.coinzilla.old.model.callback

/**
* Created by rodrigolmti on 12/02/18.
*/
open class BaseCallBack {

    open fun onSuccess() {}
    open fun onError(message: String) {}
    open fun onError() {}
}