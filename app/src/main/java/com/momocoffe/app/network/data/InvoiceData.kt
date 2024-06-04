package com.momocoffe.app.network.data

import com.momocoffe.app.network.database.Invoice

data class InvoiceState(
    val invoice: List<Invoice> = listOfNotNull(),
)

data class InvoiceProduct(
    val id: Int,
    val name: String,
    val email: String,
    val propina: String,
    val type_payment: String,
    val mount_discount: String,
    val cupon: Int,
    val iva: String,
    val sub_total: String,
    val total: String
)

