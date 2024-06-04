package com.momocoffe.app.network.dto

import com.google.gson.annotations.SerializedName
import com.momocoffe.app.network.response.Line

data class ClientEmailInvoiceRequest(
    val from: String,
    val to: String,
    val subject: String,
)



