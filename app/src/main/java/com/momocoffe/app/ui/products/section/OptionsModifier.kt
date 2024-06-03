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
import com.momocoffe.app.network.data.SelectedOptions
import com.momocoffe.app.network.response.ProductOptionsResponse
import com.momocoffe.app.network.response.ProductOptionsSizeResponse

import com.momocoffe.app.network.response.ProductsItem
import com.momocoffe.app.ui.products.components.BoxOptions
import com.momocoffe.app.ui.products.components.ItemBox
import com.momocoffe.app.ui.products.components.ItemList
import com.momocoffe.app.ui.products.components.ListOptions
import com.momocoffe.app.viewmodel.ItemModifier
import com.momocoffe.app.viewmodel.ProductsViewModel


@Composable
fun OptionsModifier(productsItem: ProductsItem,
                    onSelectID: (String) -> Unit,
                    productsViewModel: ProductsViewModel = viewModel()
) {
    val state = rememberScrollState()
    var optionsItems: ProductOptionsResponse? by remember { mutableStateOf(null) }
    var optionsSizeItems: ProductOptionsSizeResponse? by remember { mutableStateOf(null) }
    var selectedOptions by remember { mutableStateOf(SelectedOptions()) }

    LaunchedEffect(Unit) {
        productsViewModel.productOptions(product_id = productsItem.productID)
        productsViewModel.productOptionsSize(nameProduct = productsItem.nameProduct)
        onSelectID(productsItem.productID)
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

    LaunchedEffect(selectedOptions) {
        val totalPrice = selectedOptions.calculatePrice()
        if(optionsSizeItems?.sizes?.size != null){
            productsViewModel.calculatePriceResult.value =  totalPrice
        }else{
            productsViewModel.calculatePriceResult.value = (totalPrice  +  productsItem.price).toInt()
        }
    }

    LaunchedEffect(productsViewModel.productsOptionsSizeResultState.value){
        productsViewModel.productsOptionsSizeResultState.value?.let {result ->
            when{
                result.isSuccess ->{
                    val optionsResponse = result.getOrThrow()
                    optionsSizeItems = optionsResponse
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
        if(optionsSizeItems?.sizes?.isNotEmpty() == true){
            optionsSizeItems?.let { options ->
                var newsItems = mutableListOf<ItemBox>()
                if(options.sizes.isNotEmpty()){
                    options.sizes.mapIndexed { index, product ->
                        if(newsItems.none {it.name == product}){
                            newsItems.add(
                                ItemBox(
                                    options.ids[index],
                                    product,
                                    options.price[index].toInt()
                                )
                            )
                        }
                    }
                }

                if (newsItems.size > 0) {
                    BoxOptions(
                        iconResource = R.drawable.tamano_icon,
                        textResource = R.string.size,
                        onSelectPrice = { id, price, name ->
                            selectedOptions = selectedOptions.copy(size = price)
                            val updatedMap = productsViewModel.selectModifiersOptions.toMutableMap()
                            updatedMap["size"] = ItemModifier(name, price.toString())
                            productsViewModel.selectModifiersOptions = updatedMap
                            onSelectID(id)
                        },
                        items = newsItems.map { product ->
                            ItemBox(product.id, product.name, product.price)
                        },
                        defaultSelect = "chico  2oz|chico  8oz|chico  12oz|chico  16oz f"
                    )
                }
            }
        }


        if(optionsItems?.temperatura?.isNotEmpty() == true) {
            optionsItems?.let { options ->
                var newsItems = mutableListOf<ItemBox>()
                if (options.temperatura.isNotEmpty()) {
                    options.temperatura.map { product ->
                        if (newsItems.none { it.name == product.name }) {
                            newsItems.add(
                                ItemBox(
                                    product.id.toString(),
                                    product.name,
                                    product.price.toInt()
                                )
                            )
                        }
                    }
                }

                if (newsItems.size > 0) {
                    BoxOptions(
                        iconResource = R.drawable.temp_icon,
                        textResource = R.string.txt_temp,
                        onSelectPrice = { id, price, name ->
                            selectedOptions = selectedOptions.copy(temperatureFood = price)
                            val updatedMap = productsViewModel.selectModifiersOptions.toMutableMap()
                            updatedMap["temp"] = ItemModifier(name, price.toString())
                            productsViewModel.selectModifiersOptions = updatedMap
                        },
                        items = newsItems.map { product ->
                            ItemBox(product.id, product.name, product.price)
                        },
                        defaultSelect = "Al tiempo"
                        )

                }
            }
        }


        if(optionsItems?.leche?.isNotEmpty() == true) {

            optionsItems?.let { options ->

                var newsItems = mutableListOf<ItemBox>()

                if (options.leche.isNotEmpty()) {
                    options.leche.map { product ->

                        if (newsItems.none { it.name == product.name }) {

                            newsItems.add(
                                ItemBox(
                                    product.id.toString(),
                                    product.name,
                                    product.price.toInt()
                                )
                            )
                        }
                    }
                }
                if (newsItems.size > 0) {
                    BoxOptions(
                        iconResource = R.drawable.milk_icon,
                        textResource = R.string.txt_milk,
                        onSelectPrice = { id, price, name ->
                            selectedOptions = selectedOptions.copy(milk = price)
                            val updatedMap = productsViewModel.selectModifiersOptions.toMutableMap()
                            updatedMap["milk"] = ItemModifier(name, price.toString())
                            productsViewModel.selectModifiersOptions = updatedMap
                        },
                        items = newsItems.map { product ->
                            ItemBox(product.id, product.name, product.price)
                        },
                        defaultSelect = "Entera")
                }
            }
        }

        if(optionsItems?.azúcar?.isNotEmpty() == true) {
            optionsItems?.let { options ->
                var newsItems = mutableListOf<ItemBox>()
                if (options.azúcar.isNotEmpty()) {
                    options.azúcar.map { product ->
                        if (newsItems.none { it.name == product.name }) {
                            newsItems.add(
                                ItemBox(
                                    product.id.toString(),
                                    product.name,
                                    product.price.toInt()
                                )
                            )
                        }
                    }
                }

                if (newsItems.size > 0) {
                    BoxOptions(
                        iconResource = R.drawable.sugar_icon,
                        textResource = R.string.txt_sugar,
                        onSelectPrice = { id, price, name ->
                            selectedOptions = selectedOptions.copy(sugar = price)
                            val updatedMap = productsViewModel.selectModifiersOptions.toMutableMap()
                            updatedMap["sugar"] = ItemModifier(name, price.toString())
                            productsViewModel.selectModifiersOptions = updatedMap
                        },
                        items = newsItems.map { product ->
                            ItemBox(product.id, product.name, product.price)
                        },
                        defaultSelect = "Sin Azucar|Original|Sin azucar")
                }
            }
        }

        if(optionsItems?.extraShotDeCafé?.isNotEmpty() == true) {
            optionsItems?.let { options ->
                var newsItems = mutableListOf<ItemList>()
                if (options.extraShotDeCafé.isNotEmpty()) {
                    options.extraShotDeCafé.map { product ->
                        if (newsItems.none { it.name == product.name }) {
                            newsItems.add(
                                ItemList(
                                    product.id.toString(),
                                    product.name,
                                    product.price.toInt()
                                )
                            )
                        }
                    }
                }
                if (newsItems.size > 0) {
                    ListOptions(
                        iconResource = R.drawable.extra_icon,
                        onSelectPrice = { price, name ->
                            selectedOptions = selectedOptions.copy(extraShot = price)
                            val updatedMap = productsViewModel.selectModifiersList.toMutableMap()
                            updatedMap["extra"] =  ItemModifier(name, price.toString())
                            productsViewModel.selectModifiersList = updatedMap
                        },
                        items = newsItems.map { product ->
                            ItemList(product.id, product.name, product.price)
                        },
                        defaultSelect = ""
                        )
                }
            }
        }


        if(optionsItems?.tapa?.isNotEmpty() == true) {
            optionsItems?.let { options ->
                var newsItems = mutableListOf<ItemList>()

                if (options.tapa.isNotEmpty()) {
                    options.tapa.map { product ->
                        if (newsItems.none { it.name == product.name }) {
                            newsItems.add(
                                ItemList(
                                    product.id.toString(),
                                    product.name,
                                    product.price.toInt()
                                )
                            )
                        }
                    }
                }

                if (newsItems.size > 0) {
                    ListOptions(iconResource = R.drawable.tapa_icon,
                        onSelectPrice = { price, name ->
                            selectedOptions = selectedOptions.copy(tapLib = price)
                            val updatedMap = productsViewModel.selectModifiersList.toMutableMap()
                            updatedMap["libTapa"] =  ItemModifier(name, price.toString())
                            productsViewModel.selectModifiersList = updatedMap
                        },
                        items = newsItems.map { product ->
                            ItemList(product.id, product.name, product.price)
                        },
                        defaultSelect = "Sin tapa"
                        )
                }
            }
        }

        if(optionsItems?.salsas?.isNotEmpty() == true) {
            optionsItems?.let { options ->

                var newsItems = mutableListOf<ItemBox>()

                if (options.salsas.isNotEmpty()) {
                    options.salsas.map { product ->
                        if (newsItems.none { it.name == product.name }) {
                            newsItems.add(
                                ItemBox(
                                    product.id.toString(),
                                    product.name,
                                    product.price.toInt()
                                )
                            )
                        }
                    }
                }
                if (newsItems.size > 0) {
                    ListOptions(
                        iconResource = R.drawable.salsa_icon,
                        onSelectPrice = { price, name ->
                            selectedOptions = selectedOptions.copy(extraShot = price)
                            val updatedMap = productsViewModel.selectModifiersList.toMutableMap()
                            updatedMap["sauce"] =  ItemModifier(name, price.toString())
                            productsViewModel.selectModifiersList = updatedMap
                        },
                        items = newsItems.map { product ->
                            ItemList(product.id, product.name, product.price)
                        },
                        defaultSelect = "Sin salsa"
                    )

                }
            }
        }


    }
}