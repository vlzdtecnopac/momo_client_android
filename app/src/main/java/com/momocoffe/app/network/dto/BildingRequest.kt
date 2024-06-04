package com.momocoffe.app.network.dto


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

data class BuildingRequest (
    val name: String,
    @SerializedName("email_payment")
    val email: String,
    @SerializedName("shopping_id")
    val shoppingID: String,

   @SerializedName("kiosko_id")
    val kioskoID: String,

   @SerializedName("type_payment")
    val typePayment: String,

    val propina: String,

   @SerializedName("mount_receive")
    val mountReceive: String,

   @SerializedName("mount_discount")
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
   @SerializedName("toteat_xir")
    var toteatXir: String,

   @SerializedName("toteat_xil")
    var toteatXil: String,

   @SerializedName("toteat_xiu")
    var toteatXiu: String,

   @SerializedName("toteat_apitoken")
    var toteatApitoken: String,

   @SerializedName("toteat_status")
    var toteatStatus: String,

   @SerializedName("toteat_type")
    var toteatType: String,

   @SerializedName("toteat_chanel")
    var toteatChanel: String,

)
