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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.network.response.ItemShopping
import com.momocoffe.app.ui.chekout.components.OutTextField
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.viewmodel.ClientViewModel

@Composable
fun ContentTypePayment(
    shoppingItems: List<ItemShopping>,
    onCancel: () -> Unit,
    valueSubTotal: Float,
    valueCupon: Float,
    valuePropina: Float,
    valueTotal: Float,
    clientViewModel: ClientViewModel = viewModel()

) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
    val client_id = sharedPreferences.getString("clientId", null) ?: ""

    var invite by remember { mutableStateOf(value = "") }
    var validTypePayment by remember { mutableStateOf(value = 0) }
    val enterNameInvited = stringResource(id = R.string.enter_name_invitado)
    
    LaunchedEffect(Unit){
        if(client_id.isNotBlank()) {
            clientViewModel.getClient("", "", client_id)
        }
    }


    LaunchedEffect(clientViewModel.clientResultCheckEmailState.value) {
        clientViewModel.clientResultCheckEmailState.value?.let { result ->
            when {
                result.isSuccess -> {
                    val userResponse = result.getOrThrow()
                    if(userResponse.items.isNotEmpty()){
                        invite = "${userResponse.items[0].firstName} ${userResponse.items[0].lastName}"
                    }
                }
                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Log.e("Result.ClientViewModel", exception.toString())
                }
            }
        }
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
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
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
                        }else {
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
                                    "Falta por instalar MOMO Zettle Payment en tu dispositivo.",
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