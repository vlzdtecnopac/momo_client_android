package com.momocoffe.app.ui.products.section

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.momocoffe.app.BuildConfig
import com.momocoffe.app.network.response.ProductsItem
import com.momocoffe.app.ui.components.Category
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.products.components.DescriptionProduct
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.OrangeDarkLight
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.viewmodel.ProductsViewModel
import com.momocoffe.app.R
import com.momocoffe.app.network.data.CartProduct
import com.momocoffe.app.ui.theme.BlueDarkTransparent
import com.momocoffe.app.viewmodel.CartViewModel
import com.spr.jetpack_loading.components.indicators.BallClipRotatePulseIndicator

@Composable
fun Product(
    navController: NavController,
    product_id: String?,
    cartViewModel: CartViewModel,
    productsViewModel: ProductsViewModel = viewModel(),
) {

    val context = LocalContext.current
    val loading = productsViewModel.loadingState.value
    val sharedPreferences = context.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
    val preference_shopping_id = sharedPreferences.getString("shoppingId", null) ?: ""
    var productsItems: List<ProductsItem>? by remember { mutableStateOf(null) }
    var selectProductID: String by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        productsViewModel.product(preference_shopping_id, product_id)
    }

    LaunchedEffect(selectProductID){
        productsViewModel.product(preference_shopping_id, product_id)
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

    Box {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(navController, buttonExit = false)

            Column(
                modifier = Modifier
                    .background(BlueDark)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Category(navController = navController, cartViewModel)
                Spacer(modifier = Modifier.height(8.dp))
            }

            productsItems.let { product ->

                if (product?.get(0)?.productModifier?.tamaño == null) {
                    productsViewModel.calculatePriceResult.value =
                        product?.get(0)?.price?.toInt() ?: 0
                }
                Box(
                    modifier = Modifier
                        .widthIn(0.dp, 900.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Row(modifier = Modifier.padding(10.dp)) {
                            Column(
                                modifier = Modifier
                                    .weight(.3f)
                                    .padding(end = 10.dp),
                                verticalArrangement = Arrangement.Top
                            ) {
                                (product?.get(0) ?: null)?.let { DescriptionProduct(it) }
                            }
                            Column(
                                modifier = Modifier
                                    .weight(.8f)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(BlueDark)
                                    .border(
                                        width = 1.2.dp,
                                        color = BlueDark,
                                        shape = RoundedCornerShape(14.dp)
                                    )
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                (product?.get(0) ?: null)?.let {
                                    OptionsModifier(it,
                                        onSelectID = {
                                            selectProductID = it
                                        })
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Button(
                                        onClick = {
                                            Log.d("Result.ProductsViewModel", selectProductID)
                                            product?.get(0)?.let { firstProduct ->
                                                try {
                                                    val image = firstProduct.image ?: ""
                                                    cartViewModel.createProduct(
                                                        CartProduct(
                                                            "",
                                                            productId = selectProductID,
                                                            titleProduct = firstProduct.nameProduct,
                                                            imageProduct = image,
                                                            priceProduct = productsViewModel.calculatePriceResult.value.toString(),
                                                            priceProductMod = productsViewModel.calculatePriceResult.value.toString(),
                                                            countProduct = 1,
                                                            modifiersOptions = productsViewModel.selectModifiersOptions,
                                                            modifiersList = productsViewModel.selectModifiersList
                                                        )
                                                    )
                                                    Toast.makeText(
                                                        context,
                                                        R.string.add_cart_product_new,
                                                        Toast.LENGTH_LONG
                                                    )
                                                        .show()
                                                } catch (e: Exception) {
                                                    Toast.makeText(
                                                        context,
                                                        R.string.error_cart_product_new,
                                                        Toast.LENGTH_LONG
                                                    )
                                                        .show()
                                                    Log.e(
                                                        "Result.CartModelView",
                                                        e.message.toString()
                                                    )
                                                }
                                            }
                                        },
                                        modifier = Modifier
                                            .width(400.dp)
                                            .height(60.dp)
                                            .padding(horizontal = 25.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = OrangeDark,
                                            disabledBackgroundColor = OrangeDarkLight,
                                            disabledContentColor = OrangeDarkLight
                                        )
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.add_cart_payment) + " $${productsViewModel.calculatePriceResult.value}",
                                            fontSize = 18.sp,
                                            color = Color.White,
                                            fontFamily = redhatFamily,
                                            fontWeight = FontWeight(700)
                                        )
                                    }
                                }

                            }

                        }

                    }
                }
            }

        }

        if (loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueDarkTransparent)
            ) {
                BallClipRotatePulseIndicator()
            }
        }

    }

}