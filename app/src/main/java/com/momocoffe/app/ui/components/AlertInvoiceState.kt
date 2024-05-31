package com.momocoffe.app.ui.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.momocoffe.app.R
import com.momocoffe.app.network.dto.Extra
import com.momocoffe.app.network.dto.ExtraCoffee
import com.momocoffe.app.network.dto.Lid
import com.momocoffe.app.network.dto.Milk
import com.momocoffe.app.network.dto.PedidoRequest
import com.momocoffe.app.network.dto.Producto
import com.momocoffe.app.network.dto.Size
import com.momocoffe.app.network.dto.Sugar
import com.momocoffe.app.network.dto.Temperature
import com.momocoffe.app.network.dto.productosToString
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.viewmodel.CartViewModel
import com.momocoffe.app.viewmodel.PedidoViewModel

@Composable
fun AlertInvoiceState(stateInvoice: String, viewCartModel: CartViewModel, resetState: () -> Unit) {
    when (stateInvoice) {
        "completed" -> SuccessPaymentModal(viewCartModel = viewCartModel, resetState)
        "cancelled" -> CancelPaymentModal(viewCartModel = viewCartModel, resetState)
        "failed" -> ErrorPaymentModal(resetState)
        else -> {}
    }
}

@Composable
fun SuccessPaymentModal(
    viewCartModel: CartViewModel,
    resetState: () -> Unit,
    pedidoViewModel: PedidoViewModel = viewModel()
) {

    var cart = viewCartModel.state;
    Log.d("RESULT.ZettlePaymentMomo", cart.toString())


    LaunchedEffect(Unit) {
        val pedidoData = PedidoRequest(
            name_client = "Andres Gonzales",
            shopping_id = "de86589e-255f-4987-8f6b-53845acfd154",
            kiosko_id = "eae76186-6702-4216-8225-cee64e380f38",
            columns_pending = 4,
            product = productosToString(
                listOf(
                    Producto(
                        id = "3caccd2e-9842-4239-aae2-a2a3d3cced5e",
                        name_product = "Flat white",
                        price = 40,
                        image = "https://lh3.googleusercontent.com/q7eTMbvJ6FDmdCr24yHaA2Vgr4A62HTSUMPQ2A5hL3U4_oHM2KZyQ7OyhYz7cMm16kuIHBc3afRVw-lRRj01kjUD9G3Sh_TcKQ",
                        extra = Extra(
                            size = Size("Chico", 0),
                            milk = Milk("Deslactosada", 5),
                            sugar = Sugar("Blanca", 0),
                            extra_coffee = listOf(ExtraCoffee("Extra shot No", "0")),
                            lid = listOf(Lid("Sin Tapa", 0)),
                            sauce = listOf(),
                            temperature = Temperature("", 0),
                            color = "",
                            coffee_type = ""
                        ),
                        quanty = 1,
                        subtotal = 40
                    ),
                )
            )
        )
        //pedidoViewModel.create(pedidoData)
    }

    Dialog(
        onDismissRequest = {},
        DialogProperties(
            usePlatformDefaultWidth = true
        )
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .padding(0.dp)
                .widthIn(min = 460.dp, max = 830.dp)
                .heightIn(min = 410.dp, max = 420.dp)
                .zIndex(88f),
            color = BlueLight
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(BlueLight),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.clock_icon),
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    modifier = Modifier.width(145.dp),
                    contentScale = ContentScale.Fit,
                )
                Text(
                    stringResource(id = R.string.payment_receive_success_order),
                    fontFamily = redhatFamily,
                    fontSize = 28.sp,
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    stringResource(id = R.string.profile_you_receive),
                    fontFamily = redhatFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center

                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .widthIn(0.dp, 400.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .border(
                                width = 0.6.dp,
                                color = BlueDark,
                                shape = RoundedCornerShape(14.dp)
                            )
                            .weight(0.5f)
                            .height(60.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            disabledContentColor = Color.Transparent,
                            contentColor = Color.Transparent,
                            backgroundColor = Color.Transparent
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
                            stringResource(id = R.string.txt_print),
                            color = BlueDark,
                            fontSize = 22.sp,
                            fontWeight = FontWeight(700)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(0.5f)
                            .height(60.dp)
                            .padding(horizontal = 5.dp)
                            .border(
                                width = 0.6.dp,
                                color = OrangeDark,
                                shape = RoundedCornerShape(14.dp)
                            ),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = OrangeDark,
                            disabledBackgroundColor = OrangeDark,
                            disabledContentColor = OrangeDark
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
                            stringResource(id = R.string.txt_virtual),
                            fontSize = 22.sp,
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

@Composable
fun ErrorPaymentModal(
    resetState: () -> Unit
) {
    Dialog(
        onDismissRequest = {},
        DialogProperties(
            usePlatformDefaultWidth = true
        )
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .padding(0.dp)
                .widthIn(min = 460.dp, max = 830.dp)
                .heightIn(min = 510.dp, max = 520.dp)
                .zIndex(88f),
            color = BlueLight
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(BlueLight),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.momo_coffe_mug),
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    modifier = Modifier.width(150.dp),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    stringResource(id = R.string.something_went_wrong),
                    fontFamily = redhatFamily,
                    fontSize = 28.sp,
                    fontWeight = FontWeight(700)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    stringResource(id = R.string.text_try_again),
                    fontFamily = redhatFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(id = R.string.yuo_payment_could_error),
                    fontFamily = redhatFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(id = R.string.there_problem_helping),
                    fontFamily = redhatFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .widthIn(0.dp, 700.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .border(
                                width = 0.6.dp,
                                color = BlueDark,
                                shape = RoundedCornerShape(14.dp)
                            )
                            .weight(0.5f)
                            .height(60.dp),
                        onClick = {
                            resetState()
                        },
                        colors = ButtonDefaults.buttonColors(
                            disabledContentColor = Color.Transparent,
                            contentColor = Color.Transparent,
                            backgroundColor = Color.Transparent
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
                            stringResource(id = R.string.cancel),
                            color = BlueDark,
                            fontSize = 22.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(0.5f)
                            .height(60.dp)
                            .padding(horizontal = 5.dp)
                            .border(
                                width = 0.6.dp,
                                color = OrangeDark,
                                shape = RoundedCornerShape(14.dp)
                            ),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = OrangeDark,
                            disabledBackgroundColor = OrangeDark,
                            disabledContentColor = OrangeDark
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
                            text = stringResource(id = R.string.intend),
                            fontSize = 22.sp,
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
fun CancelPaymentModal(
    viewCartModel: CartViewModel,
    resetState: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewCartModel.clearAllCart()
    }

    Dialog(
        onDismissRequest = {},
        DialogProperties(
            usePlatformDefaultWidth = true
        )
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .padding(0.dp)
                .widthIn(min = 460.dp, max = 830.dp)
                .heightIn(min = 510.dp, max = 520.dp)
                .zIndex(88f),
            color = BlueLight
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(BlueLight),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.momo_coffe_mug),
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    modifier = Modifier.width(150.dp),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    stringResource(id = R.string.txt_cancel_payment),
                    fontFamily = redhatFamily,
                    fontSize = 28.sp,
                    fontWeight = FontWeight(700)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    stringResource(id = R.string.come_back_soom),
                    fontFamily = redhatFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(id = R.string.txt_cancel_payment_success),
                    fontFamily = redhatFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .border(
                            width = 0.6.dp,
                            color = BlueDark,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .width(180.dp)
                        .height(45.dp),
                    onClick = {
                        resetState()
                    },
                    colors = ButtonDefaults.buttonColors(
                        disabledContentColor = Color.Transparent,
                        contentColor = Color.Transparent,
                        backgroundColor = Color.Transparent
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
                        stringResource(id = R.string.cancel),
                        color = BlueDark,
                        fontSize = 22.sp
                    )
                }


            }
        }

    }

}
