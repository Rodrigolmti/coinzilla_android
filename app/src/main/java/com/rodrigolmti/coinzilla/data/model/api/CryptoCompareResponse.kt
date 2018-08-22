package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName

class CryptoCompareResponse<T>(
        @SerializedName("Response")
        val success: Boolean,
        @SerializedName("Message")
        val message: String,
        @SerializedName("Data")
        val data: List<T>
)