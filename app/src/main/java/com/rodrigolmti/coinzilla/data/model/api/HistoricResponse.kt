package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName

class HistoricResponse constructor(
        val time: Float,
        val close: Float,
        val high: Float,
        val low: Float,
        val open: Float,
        @SerializedName("volumefrom")
        val volumeFrom: Float,
        @SerializedName("volumeto")
        val volumeTo: Float
)