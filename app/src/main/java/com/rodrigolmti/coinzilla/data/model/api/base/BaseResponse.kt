package com.rodrigolmti.coinzilla.data.model.api.base

import com.google.gson.annotations.SerializedName

class BaseResponse<T>(
        val success: Boolean,
        val message: String,
        @SerializedName("data")
        val data: T
)