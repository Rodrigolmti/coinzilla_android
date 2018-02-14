package com.rodrigolmti.coinzilla.coinzilla.model.dto

/**
* Created by rodrigolmti on 12/02/18.
*/
class BaseDTO<out Any> (
        val success: Boolean,
        val message: String,
        val data: Any
)