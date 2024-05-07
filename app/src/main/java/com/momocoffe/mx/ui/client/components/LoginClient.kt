package com.momocoffe.mx.ui.client.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavController
import com.momocoffe.mx.R
import com.momocoffe.mx.navigation.Destination
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.ui.theme.redhatFamily
import com.momocoffe.mx.ui.theme.stacionFamily

@Composable
fun LoginClient(navController: NavController) {
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
                        painter = painterResource(id = R.drawable.client_session),
                        contentDescription = stringResource(id = R.string.momo_coffe),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(7f)
                        .background(BlueDark),
                    verticalArrangement = Arrangement.SpaceAround,

                    ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = stringResource(id = R.string.momo_coffe),
                            modifier = Modifier.width(190.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Iniciar Sesión",
                            color = Color.White,
                            fontSize = 30.sp,
                            fontFamily = redhatFamily,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Selecciona una opción de ingreso.",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontFamily = stacionFamily,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonOutLine(text = "Telefóno", icon = R.drawable.phone, onclick = {})
                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonOutLine(
                            text = "Correo Electrónico",
                            icon = R.drawable.mail_icon,
                            onclick = {})
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalAlignment = Alignment.End
                    ) {

                        ButtonBack(onclick = {
                            navController.navigate(Destination.Client.route)
                        })
                    }
                }
            }
        }
    }
}