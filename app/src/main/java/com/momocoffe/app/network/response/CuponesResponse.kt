package com.momocoffe.app.network.response
data class CuponesResponse (
    val items: List<CuponesItem>,
    val page: Long,
    val limit: Long,
    val total: Long
)

data class CuponesItem (
    val id: Long,
    val cuponID: String,
    val shopping: String,
    val nameCupon: String,
    val cuponCode: String,
    val typeDiscount: String,
    val discount: String,
    val cuponConsume: String,
    val typeVigente: String,
    val vigencia: String,
    val countCupon: Any? = null,
    val createAt: String,
    val updateAt: Any? = null
)