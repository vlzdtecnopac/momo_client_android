package com.momocoffe.app.network.response

import com.google.gson.annotations.SerializedName

data class ProductsResponse (
    val items: List<ProductsItem>,
    val page: Long,
    val limit: Long,
    val total: Long
)

data class ProductsItem (
    val id: Long,
    @SerializedName("product_id")
    val productID: String,
    @SerializedName("shopping_id")
    val shoppingID: String,
    val categorys: String,
    val subcategory: String,
    @SerializedName("name_product")
    val nameProduct: String,
    val description: String,
    val price: Long,
    @SerializedName("product_modifier_ids")
    val productModifierIDS: String,
    val state: Boolean,
    @SerializedName("products_relations")
    val productsRelations: Any? = null,
    @SerializedName("create_at")
    val createAt: String,
    @SerializedName("update_at")
    val updateAt: String,
    val image: String,
    @SerializedName("product_modifier")
    val productModifier: ProductModifier
)

data class ProductModifier (
    val tamaño: List<ItemModifier>? = null,
    val azúcar: List<ItemModifier>? = null,
    val tapa: List<ItemModifier>? = null,
    @SerializedName("extra_shot_de_cafe")
    val extraShotDeCafé: List<ItemModifier>? = null,
    val leche: List<ItemModifier>? = null
)

data class ItemModifier (
    val category: Any,
    val name: String
)


