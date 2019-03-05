package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName

class ExchangeResponse constructor(
        @SerializedName("exchange")
        val exchange: String,
        @SerializedName("toSymbol")
        val toSymbol: String,
        @SerializedName("volume24h")
        val volume24h: Double,
        @SerializedName("volume24hTo")
        val volume24hTo: Double
)