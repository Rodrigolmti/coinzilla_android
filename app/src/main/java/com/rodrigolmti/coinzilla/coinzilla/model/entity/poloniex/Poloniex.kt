package com.rodrigolmti.coinzilla.coinzilla.model.entity.poloniex

import com.google.gson.annotations.SerializedName

/**
* Created by rodrigolmti on 12/02/18.
*/
class Poloniex constructor(
        @SerializedName("tag")
        open val tag: String,
        @SerializedName("quant")
        open val quant: String,
        @SerializedName("price")
        open val price: Double
)