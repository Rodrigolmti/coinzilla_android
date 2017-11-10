package com.rodrigolmti.coinzilla.coinzilla.model.dto

import com.google.gson.annotations.SerializedName

class BaseDTO<out Any> (
        val success: Boolean,
        val token: String,
        @SerializedName("data")
        val data: List<Any>
)