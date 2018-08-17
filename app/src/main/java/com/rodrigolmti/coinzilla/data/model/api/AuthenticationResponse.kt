package com.rodrigolmti.coinzilla.data.model.api

class AuthenticationResponse(
        val success: Boolean,
        val token: String,
        val message: String
)