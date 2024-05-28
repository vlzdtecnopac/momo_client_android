package com.momocoffe.app.ui.components.cart

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.network.data.CartProductEdit
import com.momocoffe.app.network.data.CartState
import com.momocoffe.app.network.database.Cart
import com.momocoffe.app.ui.components.SolidLineDivider
import com.momocoffe.app.ui.theme.*

import com.momocoffe.app.viewmodel.CartViewModel
import com.momocoffe.app.viewmodel.ItemModifier
import com.spr.jetpack_loading.components.indicators.BallClipRotatePulseIndicator


@Composable
fun ContentCart(
    onClickOutside: () -> Unit,
    navController: NavController,
    cartViewModel: CartViewModel,
    state: CartState
) {

    val loading = cartViewModel.loadingCartState.value

    LaunchedEffect(Unit) {
        if (state.carts.isNullOrEmpty()) {
            cartViewModel.loadingCartState.value = false
        } else {
            cartViewModel.countTotal()
            cartViewModel.priceSubTotal()
        }

        cartViewModel.loadingCartState.value = false
    }

    Box {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.8f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        stringResource(id = R.string.resum_pedido),
                        color = Color.Black,
                        fontFamily = redhatFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700)
                    )

                    Text(
                        "${cartViewModel.countCartState.value ?: 0} " + stringResource(id = R.string.products),
                        color = Color.Black,
                        fontFamily = redhatFamily,
                        fontSize = 12.sp
                    )

                }
                Column(
                    modifier = Modifier
                        .weight(0.2f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(OrangeDark)
                            .border(
                                width = 0.6.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(4.dp),
                        onClick = onClickOutside
                    ) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = stringResource(id = R.string.momo_coffe),
                            tint = Color.White,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }

            }

            LazyColumn(modifier = Modifier.fillMaxHeight(0.8f)) {
                itemsIndexed(items = state.carts) { index, item ->
                    ProductCart(item, cartViewModel)
                }
            }

            TotalPayment(navController, cartViewModel)

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

@Composable
fun ProductCart(
    product: Cart,
    cartViewModel: CartViewModel,
) {

    var count by remember { mutableStateOf(value = 1) }
    val optionsSize = mutableListOf<String?>()
    val itemsModifiersOptions = parseItemModifiers(product.modifiersOptions)
    val itemsModifiersList = parseObject(product.modifiersList)

    for ((key, value) in itemsModifiersOptions) {
        optionsSize.add(value.name)
    }

    LaunchedEffect(Unit) {
        count = product.countProduct
    }

    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ){
        Row(modifier = Modifier.padding(8.dp)) {
            Column {
                if (product.imageProduct.isNullOrBlank()) {
                    Image(
                        painter = painterResource(id = R.drawable.no_found),
                        contentDescription = stringResource(id = R.string.momo_coffe),
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .width(70.dp)
                            .height(70.dp),
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(product.imageProduct)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.no_found),
                        contentDescription = stringResource(R.string.momo_coffe),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .width(70.dp)
                            .height(70.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BtnCart(
                        onClickButton = {
                            if (count > 1) {
                                count -= 1
                                cartViewModel.editCart(
                                    CartProductEdit(
                                        product.id,
                                        countProduct = count,
                                        product.priceProduct.toInt() * count
                                    )
                                )
                            }
                        },
                        icon = R.drawable.menus_icon,
                        color = Color.White,
                        iconColor = OrangeDark,
                        borderColor = OrangeDark
                    )
                    Text(
                        count.toString(),
                        modifier = Modifier.width(22.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = stacionFamily
                    )
                    BtnCart(
                        onClickButton = {
                            count += 1
                            cartViewModel.editCart(
                                CartProductEdit(
                                    product.id,
                                    countProduct = count,
                                    product.priceProduct.toInt() * count
                                )
                            )
                        },
                        icon = R.drawable.pluss_icon,
                        color = OrangeDark,
                        iconColor = Color.White,
                        borderColor = OrangeDark
                    )
                }
            }
            Column(modifier = Modifier.padding(start = 5.dp)) {

                Text(
                    product.titleProduct,
                    fontFamily = redhatFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700)
                )
                Text(
                    optionsSize.joinToString(" | "),
                    fontFamily = redhatFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = BlueDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                itemsModifiersList.forEach {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            it.name,
                            modifier = Modifier.weight(0.6f),
                            fontFamily = redhatFamily,
                            fontSize = 12.sp
                        )
                        Text(
                            it.price,
                            modifier = Modifier.weight(0.4f),
                            fontFamily = redhatFamily,
                            fontSize = 12.sp
                        )
                    }
                }

            }
        }
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(0.8f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    "\$ ${(product.priceProductMod.toInt())}",
                    fontSize = 20.sp,
                    fontFamily = stacionFamily
                )
            }
            Box(
                modifier = Modifier.weight(0.2f),
                contentAlignment = Alignment.Center
            ) {
                BtnCart(
                    onClickButton = {
                        cartViewModel.deleteProduct(product)
                    },
                    icon = R.drawable.trash_icon,
                    color = Color.White,
                    iconColor = OrangeDark,
                    borderColor = OrangeDark
                )
            }
        }
        SolidLineDivider(color= GrayDark)
    }

}


@Composable
fun TotalPayment(navController: NavController, cartViewModel: CartViewModel) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .background(GrayLight)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                "Subtotal (${cartViewModel.countCartState.value ?: 0} producto)",
                fontSize = 16.sp,
                fontFamily = redhatFamily
            )


            Spacer(modifier = Modifier.width(8.dp))

            Text(
                "$${cartViewModel.stateTotalSub.value ?: 0}",
                fontSize = 18.sp,
                fontFamily = stacionFamily
            )

        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = OrangeDark),
            onClick = {
                navController.navigate(Destination.Checkout.route)
            }) {
            Text(stringResource(id = R.string.continue_payment), fontSize = 20.sp ,color = Color.White, fontFamily = stacionFamily)
        }
    }

}

fun parseObject(input: String): List<ItemModifier> {

    val regex = Regex("""ItemModifier\(name=(.*?), price=(\d+)\)""")

    val matches = regex.findAll(input)
    val itemModifiers = matches.map { matchResult ->
        val (name, price) = matchResult.destructured
        ItemModifier(name.trim(), price)
    }.toList()

    return itemModifiers
}

fun parseItemModifiers(input: String): Map<String, ItemModifier> {
    val regex = """(\w+)=ItemModifier\(name=(\w+), price=(\d+)\)""".toRegex()
    val matches = regex.findAll(input)
    val result = mutableMapOf<String, ItemModifier>()

    for (match in matches) {
        val key = match.groupValues[1]
        val name = match.groupValues[2]
        val price = match.groupValues[3]
        result[key] = ItemModifier(name, price)
    }

    return result
}

