package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName

open class ExchangeResponse constructor(
        @SerializedName("exchange")
        open val exchange: String,
        @SerializedName("toSymbol")
        open val toSymbol: String,
        @SerializedName("volume24h")
        open val volume24h: Double,
        @SerializedName("volume24hTo")
        open val volume24hTo: Double
)