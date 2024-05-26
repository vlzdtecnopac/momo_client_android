package com.momocoffe.app.ui.chekout

import android.content.ComponentName
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import com.momocoffe.app.ui.components.Category
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.R
import com.momocoffe.app.network.database.Cart
import com.momocoffe.app.ui.chekout.components.OutlineTextField
import com.momocoffe.app.ui.components.cart.parseItemModifiers
import com.momocoffe.app.ui.components.cart.parseObject
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.stacionFamily
import com.momocoffe.app.viewmodel.CartViewModel

data class CoffeeCart(val name: String, val price: Int)

@Composable
fun Checkout(navController: NavHostController, cartViewModel: CartViewModel,) {
    val context = LocalContext.current
    var textState by rememberSaveable { mutableStateOf(value = "") }
    val focusManager = LocalFocusManager.current
    val state = cartViewModel.state;

    LaunchedEffect(Unit){
        cartViewModel.priceSubTotal()
        cartViewModel.countTotal()
    }

    val json = """
        [
            {"name": "Subtotal  (${cartViewModel.countCartState.value} productos)", "price": ${cartViewModel.stateTotalSub.value}},
            {"name": "Propina", "price": 15},
            {"name": "Cupón", "price": 15}
        ]
    """.trimIndent()

    val table_list = Gson().fromJson(json, Array<CoffeeCart>::class.java)


    Column(
        modifier = Modifier.background(BlueLight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueDark),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(navController)
            Category(navController, cartViewModel)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .widthIn(0.dp, 1100.dp)
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(BlueDark)
                    .fillMaxHeight()
                    .padding(vertical = 5.dp, horizontal = 5.dp)
            ) {
                Row(modifier = Modifier.height(110.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.barista),
                        contentDescription = stringResource(id = R.string.momo_coffe),
                        modifier = Modifier.width(105.dp),
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(stringResource(id = R.string.start_barista_working), color = Color.White)
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Icon(
                                painterResource(id = R.drawable.dollar_circle_icon),
                                contentDescription = stringResource(id = R.string.momo_coffe),
                                tint = Color.White,
                                modifier = Modifier.size(width = 20.dp, height = 20.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                stringResource(id = R.string.add_tip),
                                color = Color.White,
                                fontFamily = redhatFamily,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                ContentPropinas()
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(BlueDark)
                    .fillMaxHeight()
                    .padding(10.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(items = state.carts, itemContent = { item -> ProductCartChekout(item) })
                }

            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(0.4f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(BlueDark)
                    .fillMaxHeight()
                    .padding(4.dp),
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlineTextField(
                        label = stringResource(id = R.string.add_cupon),
                        placeholder =  stringResource(id = R.string.add_cupon),
                        icon = R.drawable.procent_cupon_icon,
                        keyboardType = KeyboardType.Text,
                        textValue = textState,
                        onValueChange = { textState = it },
                        onClickButton = { textState = "" },
                        borderColor = Color.White,
                        onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Down
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = BlueLight,
                            disabledBackgroundColor = BlueLight,
                            disabledContentColor = BlueLight
                        ),
                        elevation = ButtonDefaults.elevation(0.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_add),
                            fontSize = 16.sp,
                            color = BlueDark,
                            fontFamily = redhatFamily,
                        )
                    }
                }

                Column {
                    Column {
                        table_list.forEach { coffee ->
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp)
                            ) {
                                Text(
                                    "${coffee.name}",
                                    modifier = Modifier.weight(0.9f),
                                    fontFamily = redhatFamily,
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                                Text(
                                    "$ ${coffee.price}",
                                    modifier = Modifier.weight(0.2f),
                                    fontFamily = redhatFamily,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            try {
                                val intent = Intent()
                                intent.component = ComponentName("com.momocoffe.izettlemomo", "com.momocoffe.izettlemomo.MainActivity")
                                intent.putExtra("parametro1", "New Invoice")
                                intent.putExtra("parametro2", 300)
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Log.e("TAG", "Error al abrir la aplicación", e)
                            }

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = OrangeDark,
                            disabledBackgroundColor = OrangeDark,
                            disabledContentColor = BlueLight
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.payment),
                            fontSize = 16.sp,
                            color = Color.White,
                            fontFamily = redhatFamily,
                        )
                    }

                }

            }
        }
    }
}

@Composable
fun ProductCartChekout(product: Cart) {
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
                    Text("\$ ${product.priceProductMod}", fontSize = 20.sp, fontFamily = stacionFamily)
                }
            }

        }
    }
    Spacer(modifier = Modifier.height(5.dp))

}

@Composable
fun ContentPropinas() {
    val list = (1..4).map { it.toString() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(0.dp),
        content = {
            items(list.size) { index ->
                Card(
                    backgroundColor = Color.Transparent,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                ) {
                    Button(
                        elevation = ButtonDefaults.elevation(0.dp),
                        modifier = Modifier.fillMaxWidth(0.5f),
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(5.dp),
                        border = BorderStroke(width = 0.dp, color = BlueLight),
                        colors = ButtonDefaults.buttonColors(backgroundColor = BlueLight),
                        onClick = {

                        }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "0%",
                                color = BlueDark,
                                fontFamily = redhatFamily,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Box(modifier = Modifier.weight(0.5f)) {
                                Text(
                                    "Hoy no quiero dejar propina",
                                    color = BlueDark,
                                    fontFamily = redhatFamily,
                                    fontSize = 10.sp,
                                    lineHeight = 12.sp
                                )
                            }

                            Icon(
                                painterResource(id = R.drawable.dollar_circle_icon),
                                contentDescription = stringResource(id = R.string.momo_coffe),
                                tint = BlueDark,
                                modifier = Modifier.size(width = 18.dp, height = 18.dp)
                            )
                        }
                    }
                }

            }
        }
    )


}