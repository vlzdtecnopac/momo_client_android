package com.momocoffe.app.ui.products

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.momocoffe.app.network.response.DataKiosko
import com.momocoffe.app.network.response.ProductsItem
import com.momocoffe.app.network.response.ProductsResponse
import com.momocoffe.app.ui.components.CardProduct
import com.momocoffe.app.ui.components.Category
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueDarkTransparent
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.viewmodel.CartViewModel
import com.momocoffe.app.viewmodel.ProductsViewModel
import com.spr.jetpack_loading.components.indicators.BallClipRotatePulseIndicator

@Composable
fun Products(
    navController: NavHostController,
    category: String? = "",
    subcategory: String? = "",
    viewModelCart: CartViewModel,
    productsViewModel: ProductsViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
    val preference_shopping_id = sharedPreferences.getString("shoppingId", null) ?: ""
    var productsItems: List<ProductsItem>? by remember { mutableStateOf(null) }
    var loading = productsViewModel.loadingState.value;


    LaunchedEffect(Unit) {
        if (category != null && subcategory != null) {
            productsViewModel.products(
                shopping_id = preference_shopping_id,
                categorys = category,
                subcategory = subcategory
            )
        } else {
            if (category != null) {
                productsViewModel.products(
                    shopping_id = preference_shopping_id,
                    categorys = category
                )
            }
        }
    }

    LaunchedEffect(productsViewModel.productsResultState.value) {
        productsViewModel.productsResultState.value?.let { result ->
            when {
                result.isSuccess -> {
                    val response = result.getOrThrow()
                    productsItems = response.items.toList()
                }

                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Log.e("Result.ProductsModelView", exception.toString())
                }
            }
        }
    }

        Column(
            modifier = Modifier
                .background(BlueLight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlueDark),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Header(navController)
                Category(navController, viewModelCart)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.fillMaxWidth(0.9f)) {
                if (!loading) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(200.dp),
                        content = {
                            items(productsItems?.size ?: 0) { index ->
                                productsItems?.get(index)?.let { product ->
                                    CardProduct(navController, product)
                                }
                            }
                        }
                    )
                }
            }
        }
}