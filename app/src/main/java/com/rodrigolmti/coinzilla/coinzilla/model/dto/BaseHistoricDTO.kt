package com.rodrigolmti.coinzilla.coinzilla.model.dto

import com.google.gson.annotations.SerializedName
import com.rodrigolmti.coinzilla.coinzilla.model.entity.Historic

class BaseHistoricDTO(

        @SerializedName("Response")
        val succes: String,
        @SerializedName("Message")
        val message: String,
        @SerializedName("Data")
        val historic: List<Historic>
)