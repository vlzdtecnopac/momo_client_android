package com.momocoffe.app.ui.chekout.section

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.network.dto.BuildingRequest
import com.momocoffe.app.network.dto.ClientEmailInvoiceRequest
import com.momocoffe.app.network.dto.Extra
import com.momocoffe.app.network.dto.ExtraCoffee
import com.momocoffe.app.network.dto.Lid
import com.momocoffe.app.network.dto.Milk
import com.momocoffe.app.network.dto.Producto
import com.momocoffe.app.network.dto.Sauce
import com.momocoffe.app.network.dto.Size
import com.momocoffe.app.network.dto.Sugar
import com.momocoffe.app.network.dto.Temperature
import com.momocoffe.app.network.dto.productosToString
import com.momocoffe.app.network.response.ItemShopping
import com.momocoffe.app.ui.chekout.components.ConfirmEmailModal
import com.momocoffe.app.ui.chekout.components.ConfirmPayment
import com.momocoffe.app.ui.chekout.components.OutTextField
import com.momocoffe.app.ui.components.cart.parseItemModifiers
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.viewmodel.BuildingViewModel
import com.momocoffe.app.viewmodel.CartViewModel
import com.momocoffe.app.viewmodel.ClientViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

@Composable
fun ContentTypePayment(
    shoppingItems: List<ItemShopping>,
    onCancel: () -> Unit,
    valueSubTotal: Float,
    valueCupon: Float,
    valuePropina: Float,
    valueTotal: Float,
    navController: NavController,
    cartViewModel: CartViewModel,
    clientViewModel: ClientViewModel = viewModel(),
    buildingViewModel: BuildingViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
    val client_id = sharedPreferences.getString("clientId", null) ?: ""
    val shopping_id = sharedPreferences.getString("shoppingId", null) ?: ""
    val kiosko_id = sharedPreferences.getString("kioskoId", null) ?: ""

    var showModalConfirmPayment by remember { mutableStateOf(value = false) }
    var showModalConfirmEmail by remember { mutableStateOf(value = false) }
    var invite by remember { mutableStateOf(value = "Invitado") }
    var email by remember { mutableStateOf(value = "") }
    var productListString by remember { mutableStateOf(value = "") }
    var validTypePayment by remember { mutableStateOf(value = 0) }
    val enterNameInvited = stringResource(id = R.string.enter_name_invitado)
    val notFoundProducts = stringResource(id = R.string.validate_products)

    LaunchedEffect(Unit) {
        if (client_id.isNotBlank()) {
            clientViewModel.getClient("", "", client_id)
        }
    }

    LaunchedEffect(key1 = true) {
        SocketHandler.getSocket().on("buildings-socket", Emitter.Listener { args ->
            val data = args[0] as JSONObject
            if (data.has("resp")) {
                val respObject = data.getJSONObject("resp")
                if (respObject.has("data")) {
                    val dataArray = respObject.getJSONArray("data")
                    for (i in 0 until dataArray.length()) {
                        val buildingObject = dataArray.getJSONObject(i)
                        val bildingId = buildingObject.getString("bilding_id")
                        val shoppingId = buildingObject.getString("shopping_id")
                        val kioskoId = buildingObject.getString("kiosko_id")
                        if (shoppingId == shopping_id && kioskoId == kiosko_id) {
                            val editor = sharedPreferences.edit()
                            editor.putString("bildingId", bildingId)
                            editor.apply()
                        }
                    }
                }
            } else {
                Log.d("Result.EmailViewModel", "No 'resp' key found in JSON data")
            }


        })
    }

    LaunchedEffect(key1 = true) {
        SocketHandler.getSocket().on("building_finish_socket_app", Emitter.Listener { args ->
            val data = args[0] as JSONObject

            if (data.getString("shopping_id") == shopping_id && data.getString("kiosko_id") == kiosko_id) {
                val editor = sharedPreferences.edit()
                editor.putString("bildingId", data.getString("bilding_id"))
                editor.apply()
                showModalConfirmPayment = false
                showModalConfirmEmail = true
            }
        })
    }


    LaunchedEffect(cartViewModel.state) {
        val newProducts = cartViewModel.state.carts.mapIndexed { index, item ->
            val itemsModifiersOptions = parseItemModifiers(item.modifiersOptions)
            Producto(
                id = item.productId,
                name_product = item.titleProduct,
                price = item.priceProduct.toInt(),
                image = item.imageProduct,
                extra = Extra(
                    size = Size(
                        itemsModifiersOptions["size"]?.name ?: "",
                        itemsModifiersOptions["size"]?.price?.toInt() ?: 0
                    ),
                    milk = Milk(
                        itemsModifiersOptions["milk"]?.name ?: "",
                        itemsModifiersOptions["milk"]?.price?.toInt() ?: 0
                    ),
                    sugar = Sugar(
                        itemsModifiersOptions["sugar"]?.name ?: "",
                        itemsModifiersOptions["sugar"]?.price?.toInt() ?: 0
                    ),
                    extra_coffee = itemsModifiersOptions["extra"]?.let {
                        listOf(
                            ExtraCoffee(
                                it.name,
                                it.price
                            )
                        )
                    } ?: listOf(),
                    lid = itemsModifiersOptions["libTapa"]?.let {
                        listOf(
                            Lid(
                                it.name,
                                it.price.toInt()
                            )
                        )
                    } ?: listOf(),
                    sauce = listOf(Sauce("", 0)),
                    temperature = Temperature("", 0),
                    color = "",
                    coffee_type = Any()
                ),
                quanty = item.countProduct,
                subtotal = item.priceProductMod.toInt()
            )
        }

        productListString = productosToString(newProducts)
    }


    LaunchedEffect(clientViewModel.clientResultCheckEmailState.value) {
        clientViewModel.clientResultCheckEmailState.value?.let { result ->
            when {
                result.isSuccess -> {
                    val userResponse = result.getOrThrow()
                    if (userResponse.items.isNotEmpty()) {
                        invite =
                            "${userResponse.items[0].firstName} ${userResponse.items[0].lastName}"
                        email = userResponse.items[0].email
                    }
                }

                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Log.e("Result.ClientViewModel", exception.toString())
                }
            }
        }
    }

    LaunchedEffect(buildingViewModel.buildingResultState.value) {
        buildingViewModel.buildingResultState.value?.let { result ->
            when {
                result.isSuccess -> {
                    val userResponse = result.getOrThrow()
                    Log.d("Result.BuildingViewModel", userResponse.toString())
                }

                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Log.e("Result.BuildingViewModel", exception.toString())
                }
            }
        }
    }

    LaunchedEffect(buildingViewModel.emailResultState.value) {
        buildingViewModel.emailResultState.value?.let { result ->
            when {
                result.isSuccess -> {
                    showModalConfirmEmail = false
                    cartViewModel.clearAllCart()
                    navController.navigate(Destination.OrderHere.route)
                }

                result.isFailure -> {
                    showModalConfirmEmail = false
                    val exception = result.exceptionOrNull()
                    Log.e("Result.ShoppingModel", exception.toString())
                }

                else -> {}
            }
        }
    }

    if (showModalConfirmPayment) {
        ConfirmPayment(title = stringResource(id = R.string.please_barista_go),
            subTitle = stringResource(
                id = R.string.completed_transaccion
            ),
            onSelect = {})
    }

    if (showModalConfirmEmail) {
        val bilding_id = sharedPreferences.getString("bildingId", null) ?: ""
        ConfirmEmailModal(
            title = stringResource(id = R.string.payment_success_received_processed),
            subTitle = stringResource(id = R.string.please_enter_email_send_invoice),
            onCancel = {
                showModalConfirmEmail = false
                cartViewModel.clearAllCart()
                navController.navigate(Destination.OrderHere.route)
            },
            onSelect = { it ->
                buildingViewModel.sendClientEmailInvoice(
                    ClientEmailInvoiceRequest(
                        from = "Nueva Factura - Momo Coffe <davidvalenzuela@tecnopac.com.co>",
                        to = it,
                        subject = "Tienes Un Nueva Pedido",
                        bilding_id = bilding_id
                    )
                )
            })
    }

    shoppingItems.let {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (client_id.isEmpty()) {
                    OutTextField(
                        textValue = invite,
                        onValueChange = {
                            invite = it
                        },
                        onClickButton = { invite = "" },
                        keyboardType = KeyboardType.Text,
                        icon = painterResource(R.drawable.user)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    stringResource(id = R.string.select_yuo_payment),
                    color = Color.White,
                    fontSize = 22.sp,
                    fontFamily = redhatFamily,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (it.first().card) {
                    Button(
                        onClick = {
                            if (invite.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    enterNameInvited,
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {

                                if(valueTotal == 0f || valueTotal.isNaN()){
                                    Toast.makeText(
                                        context,
                                        notFoundProducts,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    return@Button
                                }

                                buildingViewModel.payment(
                                    invoice = BuildingRequest(
                                        name = invite,
                                        emailPayment = "",
                                        shoppingID = shopping_id,
                                        kioskoID = kiosko_id,
                                        typePayment = "card",
                                        propina = valuePropina.toString(),
                                        mountReceive = "",
                                        mountDiscount = valueCupon.toString(),
                                        cupon = "",
                                        iva = "",
                                        subtotal = valueSubTotal.toString(),
                                        total = valueTotal.toString(),
                                        state = "pending",
                                        product = productListString,
                                    )
                                )
                                try {
                                    val intent = Intent()
                                    intent.component = ComponentName(
                                        "com.momocoffe.izettlemomo",
                                        "com.momocoffe.izettlemomo.MainActivity"
                                    )
                                    intent.putExtra("zettleSubTotal", valueSubTotal)
                                    intent.putExtra("zettleMountCupon", valueCupon)
                                    intent.putExtra("zettleMountPropina", valuePropina)
                                    intent.putExtra("zettleMountTotal", valueTotal)
                                    intent.putExtra("zettleAuthorName", invite)
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Log.e("TAG", "Error al abrir la aplicación", e)
                                    Toast.makeText(
                                        context,
                                        R.string.zettle_info_install,
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                }
                            }
                        },
                        modifier = Modifier
                            .width(320.dp)
                            .height(100.dp)
                            .padding(horizontal = 5.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (validTypePayment == 1) OrangeDark else BlueLight,
                            disabledBackgroundColor = if (validTypePayment == 1) OrangeDark else BlueLight,
                            disabledContentColor = if (validTypePayment == 1) OrangeDark else BlueLight
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painterResource(id = R.drawable.credicard_icon),
                                contentDescription = stringResource(id = R.string.momo_coffe),
                                tint = if (validTypePayment == 1) Color.White else BlueDark,
                                modifier = Modifier.size(width = 43.dp, height = 43.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = stringResource(id = R.string.credicard),
                                fontSize = 18.sp,
                                color = if (validTypePayment == 1) Color.White else BlueDark,
                                fontFamily = redhatFamily,
                                fontWeight = FontWeight(700)
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (it.first().effecty) {
                    Button(
                        onClick = {
                            if (invite.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    enterNameInvited,
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                if(valueTotal == 0f || valueTotal.isNaN()){
                                    Toast.makeText(
                                        context,
                                        notFoundProducts,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    return@Button
                                }

                                buildingViewModel.payment(
                                    invoice = BuildingRequest(
                                        name = invite,
                                        emailPayment = email,
                                        shoppingID = shopping_id,
                                        kioskoID = kiosko_id,
                                        typePayment = "effecty",
                                        propina = valuePropina.toString(),
                                        mountReceive = "",
                                        mountDiscount = valueCupon.toString(),
                                        cupon = "",
                                        iva = "",
                                        subtotal = valueSubTotal.toString(),
                                        total = valueTotal.toString(),
                                        state = "pending",
                                        product = productListString,
                                    )
                                )
                                showModalConfirmPayment = true
                            }
                        },
                        modifier = Modifier
                            .width(320.dp)
                            .height(100.dp)
                            .padding(horizontal = 5.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (validTypePayment == 2) OrangeDark else BlueLight,
                            disabledBackgroundColor = if (validTypePayment == 2) OrangeDark else BlueLight,
                            disabledContentColor = if (validTypePayment == 2) OrangeDark else BlueLight
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painterResource(id = R.drawable.effecty_icon),
                                contentDescription = stringResource(id = R.string.momo_coffe),
                                tint = if (validTypePayment == 2) Color.White else BlueDark,
                                modifier = Modifier.size(width = 35.dp, height = 35.dp)
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(
                                text = stringResource(id = R.string.efecty),
                                fontSize = 18.sp,
                                color = if (validTypePayment == 2) Color.White else BlueDark,
                                fontFamily = redhatFamily,
                                fontWeight = FontWeight(700)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onCancel,
                    modifier = Modifier
                        .width(320.dp)
                        .height(60.dp)
                        .padding(horizontal = 5.dp)
                        .border(
                            width = 0.6.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(14.dp)
                        ),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        disabledBackgroundColor = Color.Transparent,
                        disabledContentColor = Color.Transparent,
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        focusedElevation = 0.dp
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = redhatFamily,
                        fontWeight = FontWeight(700)
                    )
                }

            }
        }
}