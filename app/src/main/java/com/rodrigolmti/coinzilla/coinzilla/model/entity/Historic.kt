package com.rodrigolmti.coinzilla.coinzilla.model.entity

import com.google.gson.annotations.SerializedName

open class Historic constructor (
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