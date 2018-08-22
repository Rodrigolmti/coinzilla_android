package com.rodrigolmti.coinzilla.data.model.api.base

import com.google.gson.annotations.SerializedName

class BaseListResponse<T>(
        val success: Boolean,
        val message: String,
        @SerializedName("data")
        val data: List<T>
)