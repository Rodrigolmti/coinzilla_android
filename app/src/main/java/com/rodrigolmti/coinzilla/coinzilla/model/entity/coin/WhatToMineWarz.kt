package com.rodrigolmti.coinzilla.coinzilla.model.entity.coin

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

open class WhatToMineWarz constructor (
        @PrimaryKey
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
) : RealmObject()
