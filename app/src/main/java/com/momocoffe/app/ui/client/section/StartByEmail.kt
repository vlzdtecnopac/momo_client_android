package com.momocoffe.app.ui.client.section


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.ui.theme.stacionFamily
import com.momocoffe.app.R

@Preview(widthDp = 1440, heightDp = 745, showBackground = true)
@Composable
fun StartByEmail(){
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
                    "Digita tu correo electrónico.",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = stacionFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}