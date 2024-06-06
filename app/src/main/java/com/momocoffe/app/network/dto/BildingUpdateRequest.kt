package com.momocoffe.app.network.dto

import com.google.gson.annotations.SerializedName

data class UpdateBilingRequest (
    val name: String? = null,
    @SerializedName("shopping_id")
    val shoppingID: String,
    @SerializedName("kiosko_id")
    val kioskoID: String,
    @SerializedName("type_payment")
    val typePayment: String? = null,
    @SerializedName("mount_receive")
    val mountReceive: String? = null,
    val total: String? = null,
    val subtotal: String? = null,
    val propina: String? = null,
    val iva: String? = null,
    val cupon: String? = null,
    val state: String,
    val comment: String? = "",
    @SerializedName("total_check")
    val toteatCheck: Boolean? = null,
    val status: String,
    val type: String,
    val channel: String,
    val vendorName: String
)