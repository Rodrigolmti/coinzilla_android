package com.rodrigolmti.coinzilla.coinzilla.model.entity.poloniex

open class PoloniexBalances constructor(
        open val exchange: List<PoloniexCoin>,
        open val margin: List<PoloniexCoin>,
        open val lending: List<PoloniexCoin>
)

