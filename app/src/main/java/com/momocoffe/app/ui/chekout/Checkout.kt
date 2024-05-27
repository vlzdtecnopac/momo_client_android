package com.momocoffe.app.ui.chekout

import android.content.ComponentName
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.momocoffe.app.ui.components.Category
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.R
import com.momocoffe.app.ui.chekout.components.OutlineTextField
import com.momocoffe.app.ui.chekout.section.ProductCartCheckout
import com.momocoffe.app.ui.chekout.section.ContentPropinas
import com.momocoffe.app.ui.components.DashedDivider
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.viewmodel.CartViewModel

data class CoffeeCart(val name: String, val price: Int, val type: String?)

@Composable
fun Checkout(navController: NavHostController, cartViewModel: CartViewModel) {
    val context = LocalContext.current
    var textState by rememberSaveable { mutableStateOf(value = "") }
    val subTotalProduct = cartViewModel.stateTotalSub.value
    var propina by rememberSaveable { mutableStateOf(value = 0) }
    var typePropina by rememberSaveable { mutableStateOf(value = 0) }

    var valorTotal by remember { mutableStateOf(0) }
    var valueCupon by remember { mutableStateOf(value = 0) }
    var valuePropinaPerson by remember { mutableStateOf(value = 0) }
    var valuePropina by remember { mutableStateOf(value = 0) }

    var isCuponValid by remember { mutableStateOf(false) }

    var tableList = remember { mutableStateListOf<CoffeeCart>() }
    val state = cartViewModel.state;

    val focusManager = LocalFocusManager.current

    val couponString = stringResource(id = R.string.coupon)
    val tipString = stringResource(id = R.string.tip)

    fun initTable() {
        tableList.clear()
        tableList.add(CoffeeCart("Subtotal", subTotalProduct, null))
        tableList.add(CoffeeCart(tipString, valuePropina, null))
        tableList.add(CoffeeCart("Total", valorTotal, null))
    }

    LaunchedEffect(Unit) {
        cartViewModel.priceSubTotal()
        cartViewModel.countTotal()
        initTable()
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

        if (isCuponValid) {
            valorTotal = subTotalProduct - valueCupon + valuePropina
            tableList[3] = CoffeeCart("Total", valorTotal, null)
        }
    }


    LaunchedEffect(subTotalProduct, valuePropina, isCuponValid) {
        if (isCuponValid) {
            tableList[1] = CoffeeCart(tipString, valuePropina, null)
            tableList[2] = CoffeeCart(couponString, valueCupon, "cupon")
            tableList[3] = CoffeeCart("Total", valorTotal, "total")
        } else {
            valorTotal = listOf(subTotalProduct, valuePropina).sum()
            tableList[1] = CoffeeCart(tipString, valuePropina, null)
            tableList[2] = CoffeeCart("Total", valorTotal, "total")
        }
    }


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
                    onSelectValue = { valuePropinaPerson = it },
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
                    items(items = state.carts, itemContent = { item -> ProductCartCheckout(item) })
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
                        onClick = {
                            isCuponValid = true
                            valueCupon = 35
                            tableList.clear()
                            valorTotal = subTotalProduct - valueCupon + valuePropina
                            tableList.add(CoffeeCart("Subtotal", subTotalProduct, null))
                            tableList.add(CoffeeCart(tipString, valuePropina, null))
                            tableList.add(CoffeeCart(couponString, valueCupon, "cupon"))
                            tableList.add(CoffeeCart("Total", valorTotal, null))
                            Toast.makeText(context, "Cupón Valido", Toast.LENGTH_SHORT).show()
                        },
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
                            if (coffee.type == "total") {
                                Spacer(modifier = Modifier.height(5.dp))
                                DashedDivider()
                                Spacer(modifier = Modifier.height(5.dp))
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        coffee.name,
                                        modifier = Modifier.weight(0.9f),
                                        fontFamily = redhatFamily,
                                        fontSize = 16.sp,
                                        color = Color.White
                                    )
                                    Text(
                                        "$ ${coffee.price}",
                                        modifier = Modifier.weight(0.2f),
                                        fontFamily = redhatFamily,
                                        fontSize = 20.sp,
                                        color = Color.White
                                    )
                                }
                            } else {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    if (coffee.type == "cupon") {
                                        Text(
                                            "${coffee.name}",
                                            modifier = Modifier.weight(0.5f),
                                            fontFamily = redhatFamily,
                                            fontSize = 12.sp,
                                            color = Color.White
                                        )
                                        Button(
                                            onClick = {
                                                isCuponValid = false
                                                valueCupon = 0
                                                initTable()
                                                Toast.makeText(
                                                    context,
                                                    "Se ha eliminado cúpon",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            },
                                            modifier = Modifier
                                                .weight(0.4f)
                                                .padding(horizontal = 2.dp),
                                            shape = RoundedCornerShape(50.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = OrangeDark,
                                                disabledBackgroundColor = OrangeDark,
                                                disabledContentColor = OrangeDark
                                            ),
                                            elevation = ButtonDefaults.elevation(0.dp)
                                        ) {
                                            Text(
                                                text = stringResource(id = R.string.delete),
                                                fontSize = 14.sp,
                                                color = Color.White,
                                                fontFamily = redhatFamily,
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            "$ ${coffee.price}",
                                            modifier = Modifier.weight(0.2f),
                                            fontFamily = redhatFamily,
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                    } else {

                                        Text(
                                            coffee.name,
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
                                intent.putExtra("zettleSubTotal", subTotalProduct)
                                intent.putExtra("zettleMountCupon", valueCupon)
                                intent.putExtra("zettleMountPropina", valuePropina)
                                intent.putExtra("zettleMountTotal", valorTotal)
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




