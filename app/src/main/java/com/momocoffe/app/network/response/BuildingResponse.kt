package com.momocoffe.app.network.response

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class BuildingResponse (
    val data: List<Datum>,
    val toteat: Toteat
)

@Serializable
data class Datum (
    @SerialName("bilding_id")
    val bildingID: String,

    @SerialName("shopping_id")
    val shoppingID: String,

    @SerialName("kiosko_id")
    val kioskoID: String,

    @SerialName("type_payment")
    val typePayment: String,

    val state: String
)

@Serializable
data class Toteat (
    @SerialName("orderId")
    val orderID: Long,

    val status: String,

    @SerialName("restaurantId")
    val restaurantID: Long,

    val document: Document,
    val orderReference: String,
    val operationDate: String,

    @SerialName("tableId")
    val tableID: List<Long>,

    val localNumber: Long,
    val modificationDate: String,
    val creationDate: String,
    val type: String,
    val vendorName: String,
    val channel: String,
    val orderStatus: Long
)

@Serializable
data class Document (
    val customer: Customer,
    val line: List<Line>,
    val payments: List<Payment>,
    val dispatcher: Dispatcher
)

@Serializable
data class Customer (
    val phoneNumber1: String,
    val phoneNumber2: String,
    val name: String,
    val delivery: Delivery,
    val phoneNumber: String,
    val email: String
)

@Serializable
data class Delivery (
    val city: String,
    val deliveryZone: String,
    val country: String,
    val officeOrApt: String,
    val location: String,
    val address: String,
    val postalCode: String
)

@Serializable
class Dispatcher()

@Serializable
data class Line (
    val category: String,
    val productCode: String,
    val unitPriceBeforeTax: Double,
    val tax: List<Tax>,
    val productName: String,
    val unitPriceAfterTax: Long,
    val amountAfterTax: Long,
    val productCodeToteat: Long,
    val lineNumber: Long,
    val amountBeforeTax: Double,
    val quantity: Long
)

@Serializable
data class Tax (
    val name: String,
    val value: Double
)

@Serializable
data class Payment (
    val commission: List<Tax>,
    val amountPaid: Long,
    val paymentType: Long,
    val amount: Long,
    val ref: String,
    val id: Long
)
