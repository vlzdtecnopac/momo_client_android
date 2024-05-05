package com.momocoffe.mx.ui.login

import com.momocoffe.mx.ui.theme.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Text
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

import com.momocoffe.mx.ui.login.components.EmailOutTextField
import com.momocoffe.mx.ui.login.components.ButtonField
import com.momocoffe.mx.ui.login.components.PasswordOutTextField
import com.momocoffe.mx.R
import com.momocoffe.mx.navigation.Destination


@Composable
fun Login(navController: NavHostController) {
    var email by rememberSaveable { mutableStateOf(value = "") }
    var password by rememberSaveable { mutableStateOf(value = "") }
    val focusManager = LocalFocusManager.current
    val isValidate by derivedStateOf { email.isNotBlank() && password.isNotBlank() }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(4f),
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_img),
                contentDescription = "Image MOMO",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(7f)
                .background(BlueDark),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.momo_coffe),
                modifier = Modifier.width(290.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Iniciar sesión en Kiosko",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = redhatFamily,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Digita tu correo electrónico y contraseña",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = stacionFamily,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally) // Center the inner box
                .width(480.dp)  ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    EmailOutTextField(
                        textValue = email,
                        onValueChange = { email = it },
                        onClickButton = { email = "" },
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    PasswordOutTextField(
                        textValue = password,
                        onValueChange = { password = it },
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    ButtonField(
                        onclick = {
                            navController.navigate(Destination.Wellcome.route)
                        },
                        enabled = true
                    )
                }
            }

        }
    }


}