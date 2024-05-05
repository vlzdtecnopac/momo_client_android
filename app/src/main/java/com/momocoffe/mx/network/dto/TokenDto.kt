package com.momocoffe.mx.network.dto

import com.google.gson.annotations.SerializedName

data class TokenDto(
    @SerializedName("accessToken") val accessTokenVerify: String
)
