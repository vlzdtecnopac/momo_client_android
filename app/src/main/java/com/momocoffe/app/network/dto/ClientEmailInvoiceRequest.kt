package com.momocoffe.app.network.dto

data class ClientEmailInvoiceRequest(
    val from: String,
    val to: String,
    val subject: String,
)



