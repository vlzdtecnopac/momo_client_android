package com.momocoffe.app.network.data

import com.momocoffe.app.network.database.Cart
import com.momocoffe.app.viewmodel.ItemModifier

data class CartState(
    val carts: List<Cart> = listOfNotNull(),
)

data class CartProduct(
    val id: String,
    val productId: String,
    val titleProduct: String,
    val imageProduct: String,
    val priceProduct: String,
    val priceProductMod: String,
    val countProduct: Int,
    val modifiersOptions: MutableMap<String, ItemModifier>,
    val modifiersList: MutableMap<String, ItemModifier>
)

data class CartProductEdit(
    val id: Int,
    val countProduct: Int,
    val priceProduct: Int,
)