package com.momocoffe.app.ui.client.section


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import androidx.navigation.NavController
import com.momocoffe.app.ui.theme.*
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.client.components.ButtonBack
import com.momocoffe.app.ui.client.components.ButtonContinue
import com.momocoffe.app.ui.client.components.DropDownOutline
import com.momocoffe.app.ui.client.components.OutlineTextField


@Composable
fun RegisterClient(navController: NavController) {
    val focusManager = LocalFocusManager.current
    var firstName by rememberSaveable { mutableStateOf(value = "") }
    var email by rememberSaveable { mutableStateOf(value = "") }
    var lastName by rememberSaveable { mutableStateOf(value = "") }
    var phone by rememberSaveable { mutableStateOf(value = "") }

    val checkedState = remember { mutableStateOf(true) }

    Dialog(
        onDismissRequest = {},
        DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .padding(0.dp)
                .zIndex(88f),
            color = BlueDark
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueDark),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    modifier = Modifier.width(180.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Registrame",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontFamily = redhatFamily,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(modifier = Modifier.width(520.dp)) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Digita tus datos personales.",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontFamily = stacionFamily,
                                fontWeight = FontWeight.Normal
                            )
                            OutlineTextField(
                                label = "Nombres",
                                placeholder = "Nombres",
                                icon = R.drawable.user,
                                keyboardType = KeyboardType.Text,
                                textValue = firstName,
                                onValueChange = { firstName = it },
                                onClickButton = { firstName = "" },
                                onNext = {
                                    focusManager.moveFocus(
                                        FocusDirection.Down
                                    )
                                })
                            OutlineTextField(
                                label = "Apellidos",
                                placeholder = "Apellidos",
                                icon = R.drawable.user,
                                keyboardType = KeyboardType.Text,
                                textValue = lastName,
                                onValueChange = { lastName = it },
                                onClickButton = { lastName = "" },
                                onNext = {
                                    focusManager.moveFocus(
                                        FocusDirection.Down
                                    )
                                })
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(0.3f)
                                ) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    DropDownOutline()
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(0.8f)
                                ) {
                                    OutlineTextField(
                                        label = "Número telefónico",
                                        placeholder = "Número telefónico",
                                        keyboardType = KeyboardType.Number,
                                        icon = R.drawable.phone,
                                        textValue = phone,
                                        onValueChange = { phone = it },
                                        onClickButton = { phone = "" },
                                        onNext = {
                                            focusManager.moveFocus(
                                                FocusDirection.Down
                                            )
                                        })
                                }


                            }
                            OutlineTextField(
                                label = "Email",
                                placeholder = "Juan@momo.com",
                                icon = R.drawable.mail_icon,
                                keyboardType = KeyboardType.Email,
                                textValue = email,
                                onValueChange = { email = it },
                                onClickButton = { email = "" },
                                onNext = {
                                    focusManager.moveFocus(
                                        FocusDirection.Down
                                    )
                                })
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Checkbox(
                                checked = checkedState.value,
                                onCheckedChange = { checkedState.value = it }
                            )
                            Text("Acepta nuestros Términos y Condiciones de Privacidad", color = Color.White)
                        }
                        Row(
                         horizontalArrangement = Arrangement.Center
                        ){
                            Box(
                                modifier = Modifier
                                    .weight(0.5f)
                            ) {
                                ButtonBack(onclick = {
                                    navController.navigate(Destination.Client.route)
                                })
                            }
                            Box(
                                modifier = Modifier
                                    .weight(0.5f)
                            ) {
                                ButtonContinue(onclick = {})
                            }
                        }
                    }

                }

            }
        }

    }
}