package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName
import java.util.*

open class WtmAsicResponse constructor(
        open var id: Int = 0,
        open var tag: String = "",
        open var algorithm: String = "",
        open var difficulty: String = "",
        @SerializedName("nethash")
        open var netHash: String = "",
        @SerializedName("block_reward")
        open var estReward: String = "",
        @SerializedName("block_reward24")
        open var estReward24: String = "",
        @SerializedName("market_cap")
        open var marketCap: String = "",
        open var updateDate: Date = Date()
)