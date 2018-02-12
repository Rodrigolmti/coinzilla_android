package com.rodrigolmti.coinzilla.coinzilla.model.callback

/**
 * Created by rodrigolmti on 12/02/18.
 * At Framework System
 */
open class BaseCallBack {

    open fun onSuccess() {}
    open fun onError(message: String) {}
    open fun onError() {}
}