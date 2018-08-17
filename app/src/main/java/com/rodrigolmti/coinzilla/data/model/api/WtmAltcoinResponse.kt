package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName
import java.util.*

open class WtmAltcoinResponse constructor(
        @SerializedName("_id")
        open var id: String = "",
        open var tag: String = "",
        open var algorithm: String = "",
        open var difficulty: String = "",
        open var netHash: String = "",
        open var estReward: String = "",
        open var estReward24: String = "",
        open var marketCap: String = "",
        open var volume: String = "",
        open var updateDate: Date = Date()
)
