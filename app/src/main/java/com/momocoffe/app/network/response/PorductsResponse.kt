package com.momocoffe.app.network.response

data class ProductsResponse (
    val items: List<ProductsItem>,
    val page: Long,
    val limit: Long,
    val total: Long
)

data class ProductsItem (
    val id: Long,
    val productID: String,
    val shoppingID: String,
    val categorys: String,
    val subcategory: String,
    val nameProduct: String,
    val description: String,
    val price: Long,
    val productModifierIDS: String,
    val state: Boolean,
    val productsRelations: Any? = null,
    val createAt: String,
    val updateAt: String,
    val image: String,
    val productModifier: ProductModifier
)

data class ProductModifier (
    val tamaño: List<Azúcar>? = null,
    val azúcar: List<Azúcar>? = null,
    val tapa: List<Azúcar>? = null,
    val extraShotDeCafé: List<Azúcar>? = null,
    val leche: List<Azúcar>? = null
)

data class Azúcar (
    val category: Category,
    val name: String
)

enum class Category {
    Azúcar,
    ExtraShotDeCafé,
    Leche,
    Tamaño,
    Tapa
}
