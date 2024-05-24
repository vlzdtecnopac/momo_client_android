package com.momocoffe.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momocoffe.app.network.database.Cart
import com.momocoffe.app.network.database.CartDao
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class CartState(
    val carts: List<Cart> = listOfNotNull(),
)

data class  CartProduct(
    val titleProduct: String,
    val imageProduct: String,
    val priceProduct: String
)
class CartViewModel(
    private val dao: CartDao
) : ViewModel() {

    var state by mutableStateOf(CartState())
        private set

    init {
        viewModelScope.launch {
            dao.getAllCart().collectLatest {
                state = state.copy(carts = it)
            }
        }
    }


    fun createProduct(product: CartProduct) {
        val cart =
            Cart(0,
                product.titleProduct,
                product.imageProduct,
                product.priceProduct
            )

        viewModelScope.launch {
            dao.insertCart(cart)
        }
        state = state.copy(carts = state.carts)
    }



}