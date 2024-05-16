package com.momocoffe.app.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("employee_id")
    val employeeID: String,
    @SerializedName("shopping_id")
    val shoppingID: String,
    val state: Boolean,
    val token: String
)

data class RefreshTokenResponse(
    val token: String
)