package com.momocoffe.app.network.dto


import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class BuildingRequest (
    val name: String,
    val email: String,

    @SerialName("shopping_id")
    val shoppingID: String,

    @SerialName("kiosko_id")
    val kioskoID: String,

    @SerialName("type_payment")
    val typePayment: String,

    val propina: String,

    @SerialName("mount_receive")
    val mountReceive: String,

    @SerialName("mount_discount")
    val mountDiscount: String,

    val cupon: String,
    val iva: String,
    val subtotal: String,
    val total: String,
    val state: String,
    val product: String,
    val toteat: Toteat
)

@Serializable
data class Toteat (
    @SerialName("toteat_xir")
    var toteatXir: String,

    @SerialName("toteat_xil")
    var toteatXil: String,

    @SerialName("toteat_xiu")
    var toteatXiu: String,

    @SerialName("toteat_apitoken")
    var toteatApitoken: String,

    @SerialName("toteat_status")
    var toteatStatus: String,

    @SerialName("toteat_type")
    var toteatType: String,

    @SerialName("toteat_chanel")
    var toteatChanel: String,

)
