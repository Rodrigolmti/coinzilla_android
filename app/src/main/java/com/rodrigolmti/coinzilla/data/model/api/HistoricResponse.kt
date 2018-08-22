package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName

open class HistoricResponse constructor (
        open val time: Float,
        open val close: Float,
        open val high: Float,
        open val low: Float,
        open val open: Float,
        @SerializedName("volumefrom")
        open val volumeFrom: Float,
        @SerializedName("volumeto")
        open val volumeTo: Float
)