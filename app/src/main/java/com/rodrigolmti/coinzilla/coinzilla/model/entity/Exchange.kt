package com.rodrigolmti.coinzilla.coinzilla.model.entity

import com.google.gson.annotations.SerializedName

open class Exchange constructor(
        @SerializedName("exchange")
        open val exchange: String,
        @SerializedName("toSymbol")
        open val toSymbol: String,
        @SerializedName("volume24h")
        open val volume24h: String,
        @SerializedName("volume24hTo")
        open val volume24hTo: String
)