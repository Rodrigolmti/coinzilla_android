package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName

open class CryptoCurrencyResponse(
        var id: String? = "",
        var name: String? = "",
        var rank: String? = "",
        var symbol: String? = "",
        @SerializedName("price_usd")
        var priceUsd: String? = "",
        @SerializedName("price_btc")
        var priceBtc: String? = "",
        @SerializedName("price_brl")
        var priceBrl: String? = "",
        @SerializedName("24h_volume_usd")
        var volumeUsd: String? = "",
        @SerializedName("market_cap_usd")
        var marketCapUsd: String? = "",
        @SerializedName("available_supply")
        var availableSupply: String? = "",
        @SerializedName("total_supply")
        var totalSupply: String? = "",
        @SerializedName("percent_change_1h")
        var percentChange1H: String? = "",
        @SerializedName("percent_change_24h")
        var percentChange24H: String? = "",
        @SerializedName("percent_change_7d")
        var percentChange7D: String? = "",
        @SerializedName("24h_volume_brl")
        var volumeBrl: String? = "",
        @SerializedName("market_cap_brl")
        var marketCapBrl: String? = "",
        var favorite: Boolean = false
)

