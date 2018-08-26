package com.rodrigolmti.coinzilla.data.model.api

import com.google.gson.annotations.SerializedName

class CryptoCompareResponse<T>(
        @SerializedName("Response")
        val response: String,
        @SerializedName("Message")
        val message: String,
        @SerializedName("Data")
        val data: List<T>
) {

    val success: Boolean
        get() {
            return response.isNotEmpty() && response == "Success"
        }
}