package com.momocoffe.app.ui.components.cart

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import com.google.gson.Gson
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.network.database.Cart
import com.momocoffe.app.ui.theme.*
import com.momocoffe.app.viewmodel.CartProduct
import com.momocoffe.app.viewmodel.CartState
import com.momocoffe.app.viewmodel.CartViewModel

data class Coffee(val name: String, val price: Int)

@Composable
fun ContentCart(
    onClickOutside: () -> Unit,
    navController: NavController,
    cartViewModel: CartViewModel,
    state: CartState
) {

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
                    "${state.carts.size} " + stringResource(id = R.string.products),
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
            items(items = state.carts, itemContent = { item -> ProductCart(item, cartViewModel) })
        }
        TotalPayment(navController)
    }
}

@Composable
fun ProductCart(product: Cart,  cartViewModel: CartViewModel) {
    val optionsSize = listOf("Chico", "Regular", "Menos azúcar", "Sin tapa")
    val json = """
        [
            {"name": "Extra de café", "price": 10},
            {"name": "Another Coffee", "price": 15}
        ]
    """.trimIndent()

    val coffees = Gson().fromJson(json, Array<Coffee>::class.java)

    Column {
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
                        onClickButton = {},
                        icon = R.drawable.menus_icon,
                        color = Color.White,
                        iconColor = OrangeDark,
                        borderColor = OrangeDark
                    )
                    Text(
                        "1",
                        modifier = Modifier.width(22.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = stacionFamily
                    )
                    BtnCart(
                        onClickButton = {},
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
                coffees.forEach { coffee ->
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Text(
                            "${coffee.name}",
                            modifier = Modifier.weight(0.6f),
                            fontFamily = redhatFamily,
                            fontSize = 12.sp
                        )
                        Text(
                            "${coffee.price}",
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
                Text("\$ ${product.priceProduct}", fontSize = 20.sp, fontFamily = stacionFamily)
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

    }

}


@Composable
fun TotalPayment(navController: NavController) {
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
            horizontalArrangement = Arrangement.Start
        ) {
            Text("Subtotal (1 producto)", fontSize = 16.sp, fontFamily = redhatFamily)
            Spacer(modifier = Modifier.width(8.dp))
            Text("\$ 67.00", fontSize = 18.sp, fontFamily = stacionFamily)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(backgroundColor = OrangeDark),
            onClick = {
                navController.navigate(Destination.Checkout.route)
            }) {
            Text("Continuar al pago", color = Color.White, fontFamily = stacionFamily)
        }
    }

}


