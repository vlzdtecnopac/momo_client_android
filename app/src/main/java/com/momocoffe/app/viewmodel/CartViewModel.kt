package com.momocoffe.app.viewmodel

import android.util.Log
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

data class CartProduct(
    val id: Int,
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

class CartViewModel(
    private val dao: CartDao
) : ViewModel() {
    val loadingCartState = mutableStateOf(false)

    val countCartState = mutableStateOf(0)

    var stateTotalSub = mutableStateOf(0)
    var state by mutableStateOf(CartState())
        private set

    init {
        viewModelScope.launch {
            dao.getAllCart().collectLatest {
                state = state.copy(carts = it)
                loadingCartState.value = false
            }
        }
    }

    fun deleteProduct(product: Cart) {
        loadingCartState.value = true
        viewModelScope.launch {
            dao.deleteProduct(product)
            loadingCartState.value = false
        }
    }

    fun createProduct(product: CartProduct) {
        loadingCartState.value = true
        val cart = Cart(
            0,
            product.titleProduct,
            product.imageProduct,
            product.priceProduct,
            product.priceProductMod,
            product.countProduct,
            product.modifiersOptions.toString(),
            product.modifiersList.toString()
        )

        viewModelScope.launch {
            dao.insertCart(cart)
            state = state.copy(carts = state.carts)
            loadingCartState.value = false
        }
    }

    fun editCart(product: CartProductEdit) {
        loadingCartState.value = true
        viewModelScope.launch {
            val cart = state.carts.find { it.id == product.id }
            if (cart != null) {
                dao.updateProductCountById(cart.id, product.countProduct, product.priceProduct)
            }
            loadingCartState.value = false
        }
    }

    fun priceSubTotal() {
        try {
            if (state.carts.isNullOrEmpty()) {
                loadingCartState.value = false
            } else {
                loadingCartState.value = true
                viewModelScope.launch {
                    dao.getAllSumTotal().collectLatest { sumTotal ->
                        stateTotalSub.value = sumTotal ?: 0
                        loadingCartState.value = false
                    }
                }
            }
        }catch (e:Exception){
            Log.e("Result.CartViewModel", e.message.toString())
        }
    }
    fun countTotal() {
        try {
            if (state.carts.isNullOrEmpty()) {
                loadingCartState.value = false
            } else {
                loadingCartState.value = true
                viewModelScope.launch {
                    dao.getAllCountTotal().collectLatest { countTotal ->
                        countCartState.value = countTotal ?: 0
                        loadingCartState.value = false
                    }
                }
            }
        }catch (e:Exception){
            Log.e("Result.CartViewModel", e.message.toString())
        }
    }
}
