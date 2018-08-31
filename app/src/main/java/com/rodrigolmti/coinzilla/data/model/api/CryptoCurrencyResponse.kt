package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName
import org.parceler.ParcelConstructor
import org.parceler.Parcel

@Parcel(Parcel.Serialization.BEAN)
open class CryptoCurrencyResponse @ParcelConstructor constructor(
        @SerializedName("_id")
        var id: String? = "",
        var tag: String? = "",
        var name: String? = "",
        var slug: String? = "",
        var circulatingSupply: Double? = 0.0,
        var totalSupply: Double? = 0.0,
        var maxSupply: Double? = 0.0,
        var quoteUsd: QuoteResponse?,
        var quoteBrl: QuoteResponse?
)