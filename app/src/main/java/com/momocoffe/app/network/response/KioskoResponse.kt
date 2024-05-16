package com.momocoffe.app.network.response

data class KioskoResponse (
    val nameShopping: String,
    val data: DataKiosko
)

data class DataKiosko (
    val id: Long,
    val kioskoID: String,
    val shoppingID: String,
    val state: Boolean,
    val nombre: String,
    val createAt: String,
    val updateAt: String
)