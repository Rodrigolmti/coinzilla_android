package com.rodrigolmti.coinzilla.data.model.api

open class WtmAltcoinResponse constructor(
        open var tag: String = "",
        open var algorithm: String = "",
        open var difficulty: String = "",
        open var netHash: String = "",
        open var estReward: String = "",
        open var estReward24: String = "",
        open var marketCap: String = ""
)
