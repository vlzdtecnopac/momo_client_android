package com.momocoffe.mx.ui.client

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.R
import com.momocoffe.mx.ui.client.components.ButtonOutLine
import com.momocoffe.mx.ui.theme.redhatFamily
import com.momocoffe.mx.ui.theme.stacionFamily

@Preview(widthDp = 1440, heightDp = 780)
@Composable
fun Client(){
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
                modifier = Modifier.width(190.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "¡Bienvenid@!",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = redhatFamily,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Regístrate y descubre un mundo\n" +
                        "de beneficios.",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = stacionFamily,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonOutLine(text = "Cliente registrado", icon = R.drawable.user, onclick = {} )
            Spacer(modifier = Modifier.height(16.dp) )
            ButtonOutLine(text = "Crear Cuenta", icon = R.drawable.user_square, onclick = {})
            Spacer(modifier = Modifier.height(16.dp))
            ButtonOutLine(text = "Ordenar sin registrar", icon = R.drawable.user_octagon, onclick = {})

        }
    }
}