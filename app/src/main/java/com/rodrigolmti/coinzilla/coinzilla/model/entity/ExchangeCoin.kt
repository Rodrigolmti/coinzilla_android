package com.rodrigolmti.coinzilla.coinzilla.model.entity

import com.google.gson.annotations.SerializedName

class ExchangeCoin (

        @SerializedName("ProofType")
        val name: String,
        @SerializedName("Exchanges")
        val exchanges: List<Exchange>
)