package com.rodrigolmti.coinzilla.coinzilla.model.entity

import com.google.gson.annotations.SerializedName

open class Exchange constructor(
        @SerializedName("TOSYMBOL")
        open val type: String,
        @SerializedName("MARKET")
        open val market: String,
        @SerializedName("PRICE")
        open val price: String
)