package com.momocoffe.app.network.dto

import com.google.gson.annotations.SerializedName;
data class LoginDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
