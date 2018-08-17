package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName

class WtmAltcoinListResponse(
        val success: Boolean,
        val token: String,
        val message: String,
        @SerializedName("data")
        val data: List<WtmAltcoinResponse>
)