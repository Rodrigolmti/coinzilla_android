package com.rodrigolmti.coinzilla.coinzilla.model.entity.poloniex

import com.google.gson.annotations.SerializedName

open class PoloniexBalances constructor(
        @SerializedName("exchange")
        open val exchange: List<Poloniex>,
        @SerializedName("margin")
        open val margin: List<Poloniex>,
        @SerializedName("lending")
        open val lending: List<Poloniex>
)