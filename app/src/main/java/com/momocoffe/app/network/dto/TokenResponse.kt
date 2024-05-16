package com.momocoffe.app.network.dto

data class LoginResponse(
    val token: String,
    val userId: String
)