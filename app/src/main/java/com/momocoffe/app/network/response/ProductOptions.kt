package com.momocoffe.app.network.response

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class ProductOptionsResponse (
    @SerializedName("Azúcar")
    val azúcar: List<ItemProductModifier>,

    @SerializedName("Leche")
    val leche: List<ItemProductModifier>,

    @SerializedName("Tamaño")
    val tamaño: List<ItemProductModifier>,

    @SerializedName("Extra  Shot de Café")
    val extraShotDeCafé: List<ItemProductModifier>,

    @SerializedName("Tapa")
    val tapa: List<ItemProductModifier>,

    @SerializedName("Temperatura de bebidas")
    val temperaturaDeBebidas: List<ItemProductModifier>,

    @SerializedName("Temperatura de Alimento")
    val temperaturaDeAlimentos: List<ItemProductModifier>,

    @SerializedName("Salsas")
    val salsas: List<ItemProductModifier>
)


data class ItemProductModifier (
    val id: Long,

    @SerializedName("product_id")
    val productID: String,

    @SerializedName("categoryId")
    val categoryID: String,

    val minQuantity: String,
    val multi: Boolean,
    val sorting: String,
    val name: String,
    val maxQuantity: String,

    @SerializedName("create_at")
    val createAt: String,

    @SerializedName("update_at")
    val updateAt: String,

    @SerializedName("sub_modifier")
    val subModifier: JsonElement? = null,

    @SerializedName("modifiers_id")
    val modifiersID: String,

    val price: String,
    val localCode: String,
    val modificationDate: String,
    val alcohol: Boolean,
    val category: String,
    val modifiers: String,
    val isModifier: Boolean,
    val tamano: JsonElement? = null,
    val type: JsonElement? = null,

    @SerializedName("sub_category")
    val subCategory: JsonElement? = null
)

