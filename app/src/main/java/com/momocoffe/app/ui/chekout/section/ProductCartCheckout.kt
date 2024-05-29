package com.momocoffe.app.ui.chekout.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.momocoffe.app.R
import com.momocoffe.app.network.database.Cart
import com.momocoffe.app.ui.components.cart.parseItemModifiers
import com.momocoffe.app.ui.components.cart.parseObject
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.ui.theme.stacionFamily

@Composable
fun ProductCartCheckout(
    product: Cart,
) {
    val optionsSize = mutableListOf<String?>()
    val itemsModifiersOptions = parseItemModifiers(product.modifiersOptions)
    val itemsModifiersList = parseObject(product.modifiersList)

    for ((key, value) in itemsModifiersOptions) {
        optionsSize.add(value.name)
    }

    Card {
        Column {
            Row(modifier = Modifier.padding(8.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier.clip(shape = RoundedCornerShape(8.dp)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            "x${product.countProduct}",
                            modifier = Modifier
                                .width(30.dp)
                                .height(20.dp)
                                .background(BlueLight)
                                .padding(top = 4.dp),
                            textAlign = TextAlign.Center,
                            fontFamily = stacionFamily,
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
                            modifier = Modifier.padding(horizontal = 10.dp)
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
                        "\$ ${product.priceProductMod}",
                        fontSize = 20.sp,
                        fontFamily = stacionFamily
                    )
                }
            }

        }
    }
    Spacer(modifier = Modifier.height(5.dp))

}