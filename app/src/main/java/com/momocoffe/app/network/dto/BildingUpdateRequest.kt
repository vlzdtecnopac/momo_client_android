package com.momocoffe.app.network.dto

data class UpdateBilingRequest (
    val name: String,
    val shoppingID: String,
    val kioskoID: String,
    val typePayment: String,
    val mountReceive: String? = null,
    val total: String? = null,
    val subtotal: String? = null,
    val propina: String? = null,
    val iva: String? = null,
    val cupon: String? = null,
    val state: String,
    val comment: String,
    val toteatCheck: Boolean? = null,
    val status: String,
    val type: String,
    val channel: String,
    val vendorName: String
)