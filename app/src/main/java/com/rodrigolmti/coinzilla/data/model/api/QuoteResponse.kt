package com.rodrigolmti.coinzilla.data.model.api

import org.parceler.Parcel

@Parcel(Parcel.Serialization.FIELD)
class QuoteResponse(
        var price: Double = 0.0,
        var volume24: Double = 0.0,
        var percentChange1H: Double = 0.0,
        var percentChange24H: Double = 0.0,
        var percentChange7D: Double = 0.0,
        var marketCap: Double = 0.0
)