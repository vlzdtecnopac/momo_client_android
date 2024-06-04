package com.momocoffe.app.network.dto

import com.google.gson.annotations.SerializedName
import com.momocoffe.app.network.response.Line

data class ClientEmailInvoiceRequest(
    val from: String,
    val to: String,
    val subject: String,
    @SerializedName("order_id")
    val orderID: String,
    @SerializedName("restaurant_id")
    val restaurantID: String,
    @SerializedName("date_invoice")
    val dateInvoice: String,
    @SerializedName("type_payment")
    val typePayment: String,
    @SerializedName("mount_cupon")
    val mountCupon: String,
    @SerializedName("mount_propina")
    val mountPropina: String,
    @SerializedName("mount_sub_total")
    val mountSubtotal: String,
    @SerializedName("mount_total")
    val mountTotal: String,
    val line: List<Line>
)

data class LineProductToteat (
    val category: String,
    @SerializedName("product_code")
    val productCode: String,
    @SerializedName("unit_price_before_tax")
    val unitPriceBeforeTax: Double,
    val tax: List<Tax>,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("unit_price_after_tax")
    val unitPriceAfterTax: Long,
    @SerializedName("amount_after_tax")
    val amountAfterTax: Long,
    @SerializedName("product_code_toteat")
    val productCodeToteat: Long,
    @SerializedName("line_number")
    val lineNumber: Long,
    @SerializedName("amount_before_tax")
    val amountBeforeTax: Double,
    val quantity: Long
)

data class Tax (
    val name: String,
    val value: Double
)
