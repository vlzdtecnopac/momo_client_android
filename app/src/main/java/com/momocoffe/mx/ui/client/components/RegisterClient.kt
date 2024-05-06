package com.momocoffe.mx.ui.client.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.momocoffe.mx.R
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.ui.theme.redhatFamily
import com.momocoffe.mx.ui.theme.stacionFamily

@Preview(widthDp = 1440, heightDp = 790, name="Register Client")
@Composable
fun RegisterClient() {
    val focusManager = LocalFocusManager.current
    var firstName by rememberSaveable { mutableStateOf(value = "") }
    var email by rememberSaveable { mutableStateOf(value = "") }
    var lastName by rememberSaveable { mutableStateOf(value = "") }
    var phone by rememberSaveable { mutableStateOf(value = "") }


    Column(
        modifier = Modifier.background(BlueDark),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.momo_coffe),
            modifier = Modifier.width(290.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Registrame",
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = redhatFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier.width(520.dp)){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    "Digita tus datos personales.",
                    color = Color.White,
                    fontSize = 20.sp,
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
                    onNext = { focusManager.moveFocus(
                        FocusDirection.Down) })
                OutlineTextField(
                    label = "Apellidos",
                    placeholder = "Apellidos",
                    icon = R.drawable.user,
                    keyboardType = KeyboardType.Text,
                    textValue = lastName,
                    onValueChange = { lastName = it },
                    onClickButton = { lastName = "" },
                    onNext = { focusManager.moveFocus(
                        FocusDirection.Down) })
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
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
                            onNext = { focusManager.moveFocus(
                                FocusDirection.Down) })
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
                    onNext = { focusManager.moveFocus(
                        FocusDirection.Down) })
            }

        }

    }
}