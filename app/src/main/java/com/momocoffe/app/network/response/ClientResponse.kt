package com.momocoffe.app.network.response

import com.google.gson.annotations.SerializedName

data class ClientResponse (
    @SerializedName("client_id")
    val clientID: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val phone: String,
    val email: String
)