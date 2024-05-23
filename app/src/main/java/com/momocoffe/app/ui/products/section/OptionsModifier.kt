package com.momocoffe.app.ui.products.section

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.momocoffe.app.R
import com.momocoffe.app.network.response.ProductOptionsResponse

import com.momocoffe.app.network.response.ProductsItem
import com.momocoffe.app.ui.products.components.BoxOptions
import com.momocoffe.app.ui.products.components.ItemBox
import com.momocoffe.app.ui.products.components.ItemList
import com.momocoffe.app.ui.products.components.ListOptions
import com.momocoffe.app.viewmodel.ProductsViewModel



@Composable
fun OptionsModifier(productsItem: ProductsItem, productsViewModel: ProductsViewModel = viewModel()) {
    val state = rememberScrollState()
    var optionsItems: ProductOptionsResponse? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        Log.d("Result.ProductsModelView", productsItem.productID)
        productsViewModel.productOptions(product_id = productsItem.productID)
    }

    LaunchedEffect(productsViewModel.productsOptionsResultState.value){
        productsViewModel.productsOptionsResultState.value?.let { result ->
            when{
                result.isSuccess ->{
                    val optionsResponse = result.getOrThrow()
                    optionsItems = optionsResponse
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(horizontal = 8.dp)
            .verticalScroll(state)
    ) {
        optionsItems?.let { options ->
            var newsItems = mutableListOf<ItemBox>()

            options.azúcar.map { product ->
                if (newsItems.none { it.name == product.name }) {
                    newsItems.add(ItemBox(product.id.toInt(), product.name, product.price.toInt()))
                }
            }

            BoxOptions(
                iconResource = R.drawable.tamano_icon,
                textResource = R.string.size,
                items = newsItems.map { product ->
                    ItemBox(product.id, product.name, product.price)
                })
        }

        optionsItems?.let { options ->

            var newsItems = mutableListOf<ItemBox>()

            options.leche.map { product ->
                if (newsItems.none { it.name == product.name }) {
                    newsItems.add(ItemBox(product.id.toInt(), product.name, product.price.toInt()))
                }
            }

            BoxOptions(
            iconResource = R.drawable.tamano_icon,
            textResource = R.string.size,
            items = newsItems.map { product ->
                ItemBox(product.id, product.name, product.price)
            })
        }

        optionsItems?.let { options ->
            var newsItems = mutableListOf<ItemList>()

            options.tapa.map { product ->
                if (newsItems.none { it.name == product.name }) {
                    newsItems.add(ItemList(product.id.toInt(), product.name, product.price.toInt()))
                }
            }

            ListOptions(iconResource = R.drawable.tamano_icon,
            items =  newsItems.map { product ->
                ItemList(product.id, product.name, product.price)
            })
        }
    }
}