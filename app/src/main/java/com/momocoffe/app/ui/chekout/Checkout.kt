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
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.momocoffe.app.ui.components.Category
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.R
import com.momocoffe.app.network.database.Cart
import com.momocoffe.app.ui.chekout.components.OutlineTextField
import com.momocoffe.app.ui.chekout.components.PropinaModal
import com.momocoffe.app.ui.components.cart.parseItemModifiers
import com.momocoffe.app.ui.components.cart.parseObject
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.stacionFamily
import com.momocoffe.app.viewmodel.CartViewModel

data class CoffeeCart(val name: String, val price: Int)

@Composable
fun Checkout(navController: NavHostController, cartViewModel: CartViewModel) {
    val context = LocalContext.current
    var textState by rememberSaveable { mutableStateOf(value = "") }
    val subTotalProduct = cartViewModel.stateTotalSub.value
    var propina by rememberSaveable { mutableStateOf(value = 0) }
    var typePropina by rememberSaveable { mutableStateOf(value = 0) }
    var valuePropinaPerson by remember { mutableStateOf(value = 0) }
    var valuePropina by remember { mutableStateOf(value = 0) }
    val focusManager = LocalFocusManager.current
    val state = cartViewModel.state;

    LaunchedEffect(Unit) {
        cartViewModel.priceSubTotal()
        cartViewModel.countTotal()
    }

    LaunchedEffect(key1 = propina, key2 = typePropina) {
        valuePropina = when (propina) {
            1 -> (subTotalProduct * 5) / 100
            2 -> (subTotalProduct * 10) / 100
            3 -> (subTotalProduct * 15) / 100
            4 -> {
                when (typePropina) {
                    1 -> (subTotalProduct * valuePropinaPerson) / 100
                    2 -> valuePropinaPerson
                    else -> 0
                }
            }
            else -> 0
        }
    }


    val tableList: MutableList<CoffeeCart> =  mutableListOf()
    val valorTotal = listOf(subTotalProduct,  valuePropina).sum()
    tableList.add( CoffeeCart( "Subtotal", subTotalProduct))
    tableList.add( CoffeeCart(stringResource(id = R.string.tip), valuePropina))
    tableList.add( CoffeeCart(stringResource(id = R.string.coupon), 35))
    tableList.add( CoffeeCart("Total", valorTotal))


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
                        Text(
                            stringResource(id = R.string.start_barista_working),
                            color = Color.White
                        )
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
                ContentPropinas(
                    onSelectValue = {valuePropinaPerson = it},
                    onSelectPropina = { propina = it },
                    onTypePropina = { typePropina = it }
                )
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
                        placeholder = stringResource(id = R.string.add_cupon),
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
                        tableList.forEach { coffee ->
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
                                intent.component = ComponentName(
                                    "com.momocoffe.izettlemomo",
                                    "com.momocoffe.izettlemomo.MainActivity"
                                )
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
fun ProductCartChekout(
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

data class ListItemPropina(
    val title: String,
    val percentage: String,
    val active: Boolean
)

@Composable
fun ContentPropinas(
    onSelectValue:(Int) -> Unit,
    onSelectPropina: (Int) -> Unit,
    onTypePropina: (Int) -> Unit
) {

    var typePropina by remember { mutableStateOf(0) }
    var mountPropinaSelected by remember { mutableStateOf(0) }

    if (typePropina == 1) {
        PropinaModal(
            title = stringResource(id = R.string.write_percent),
            onCancel = { typePropina = 10 },
            onSelect = {
                onSelectPropina(4)
                onTypePropina(1)
                onSelectValue(it)
                typePropina = 10
            })
    }

    if (typePropina == 2) {
        PropinaModal(
            title = stringResource(id = R.string.write_amount_peso),
            onCancel = { typePropina = 10 },
            onSelect = {
                onSelectPropina(4)
                onTypePropina(2)
                onSelectValue(it)
                typePropina = 10
            })
    }

    val optionsPropinas = listOf(
        ListItemPropina(
            title = stringResource(id = R.string.propina_never),
            percentage = "0%",
            active = true
        ),
        ListItemPropina(
            title = stringResource(id = R.string.extra_us_hero),
            percentage = "5%",
            active = false
        ),
        ListItemPropina(
            title = stringResource(id = R.string.extra_us_hero),
            percentage = "10%",
            active = false
        ),
        ListItemPropina(
            title = stringResource(id = R.string.gesture_incredible),
            percentage = "15%",
            active = false
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(0.dp),
        content = {
            items(optionsPropinas.size) { index ->
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
                        border = BorderStroke(
                            width = 0.dp,
                            color = if (mountPropinaSelected == index) OrangeDark else BlueLight
                        ),
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (mountPropinaSelected == index) OrangeDark else BlueLight),
                        onClick = {
                            mountPropinaSelected = index
                            onSelectPropina(index)
                        }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                optionsPropinas[index].percentage,
                                color = if (mountPropinaSelected == index) Color.White else BlueDark,
                                fontFamily = redhatFamily,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Box(modifier = Modifier.weight(0.5f)) {
                                Text(
                                    optionsPropinas[index].title,
                                    color = if (mountPropinaSelected == index) Color.White else BlueDark,
                                    fontFamily = redhatFamily,
                                    fontSize = 10.sp,
                                    lineHeight = 12.sp
                                )
                            }

                            Icon(
                                painterResource(id = R.drawable.dollar_circle_icon),
                                contentDescription = stringResource(id = R.string.momo_coffe),
                                tint = if (mountPropinaSelected == index) Color.White else BlueDark,
                                modifier = Modifier.size(width = 18.dp, height = 18.dp)
                            )
                        }
                    }
                }

            }
        }
    )

    Row(
        modifier = Modifier.padding(start = 3.dp, end = 3.dp)
    ) {
        Button(
            elevation = ButtonDefaults.elevation(0.dp),
            modifier = Modifier.fillMaxWidth(0.5f),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(
                width = 0.dp,
                color = if (mountPropinaSelected == 4) OrangeDark else BlueLight
            ),
            colors = ButtonDefaults.buttonColors(backgroundColor = if (mountPropinaSelected == 4) OrangeDark else BlueLight),
            onClick = {
                mountPropinaSelected = 4
            }) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(id = R.string.other),
                    color = if (mountPropinaSelected == 4) Color.White else BlueDark,
                    fontFamily = redhatFamily,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(modifier = Modifier.weight(0.5f)) {
                    Text(
                        stringResource(id = R.string.yuo_decided),
                        color = if (mountPropinaSelected == 4) Color.White else BlueDark,
                        fontFamily = redhatFamily,
                        fontSize = 10.sp,
                        lineHeight = 12.sp
                    )
                }

                Icon(
                    painterResource(id = R.drawable.dollar_circle_icon),
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    tint = if (mountPropinaSelected == 4) Color.White else BlueDark,
                    modifier = Modifier.size(width = 18.dp, height = 18.dp)
                )
            }
        }
        if (mountPropinaSelected == 4) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
            ) {
                Button(
                    elevation = ButtonDefaults.elevation(0.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .height(41.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(
                        width = 0.dp,
                        color = if (typePropina == 1) OrangeDark else BlueLight
                    ),
                    colors = ButtonDefaults.buttonColors(backgroundColor = if (typePropina == 1) OrangeDark else BlueLight),
                    onClick = {
                        typePropina = 1
                    }
                ) {
                    Text(
                        "%",
                        color = if (typePropina == 1) Color.White else BlueDark,
                        fontFamily = redhatFamily,
                        fontSize = 20.sp,
                        lineHeight = 12.sp
                    )
                }
                Button(
                    elevation = ButtonDefaults.elevation(0.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .height(41.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(
                        width = 0.dp,
                        color = if (typePropina == 2) OrangeDark else BlueLight
                    ),
                    colors = ButtonDefaults.buttonColors(backgroundColor = if (typePropina == 2) OrangeDark else BlueLight),
                    onClick = {
                        typePropina = 2
                    }
                ) {
                    Text(
                        "$",
                        color = if (typePropina == 2) Color.White else BlueDark,
                        fontFamily = redhatFamily,
                        fontSize = 20.sp,
                        lineHeight = 12.sp
                    )
                }
            }
        }

    }
}


