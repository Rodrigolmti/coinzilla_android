package com.rodrigolmti.coinzilla.coinzilla.model.entity.poloniex

/**
* Created by rodrigolmti on 12/02/18.
*/
open class PoloniexCoin constructor(
        open var tag: String,
        open var quant: String,
        open var price: Double
)