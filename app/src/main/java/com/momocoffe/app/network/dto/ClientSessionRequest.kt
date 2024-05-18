package com.momocoffe.app.network.dto

data class ClientSessionEmailRequest(
    val email: String
)

data class ClientSessionPhoneRequest(
    val phone: String,
    val code: String
)
