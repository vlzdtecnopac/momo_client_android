package com.momocoffe.app.ui.wellcome


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.foundation.*
import androidx.compose.ui.unit.*
import com.momocoffe.app.ui.theme.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.spr.jetpack_loading.components.indicators.BallBeatIndicator
import com.momocoffe.app.ui.wellcome.component.CardOption
import com.momocoffe.app.R



import kotlinx.coroutines.delay


@Composable
fun WellCome(navController: NavController) {

    var hasNavigated = false

    LaunchedEffect(Unit) { // LaunchedEffect for delayed navigation
        delay(4000)
        if (!hasNavigated) {
            navController.navigate("orderhere")
            hasNavigated = true
        }
    }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(BlueDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

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
            "Antes de comenzar,\n" +
                    "Espera que se emparejen\n" +
                    "tus dispositivos.",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = stacionFamily,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val textModifier = Modifier.padding(5.dp)
            Column(modifier = textModifier) {
                CardOption(
                    text = "Kiosko \n Tienda 1",
                    icon = painterResource(R.drawable.kiosko),
                    color = Color.White,
                    textColor = BlueDark
                )
            }

            Column(
                modifier = Modifier.height(200.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BallBeatIndicator()
            }

            Column(modifier = textModifier) {
                CardOption(
                    text = "KDS \n Kiosko 1",
                    icon = painterResource(R.drawable.kds_off),
                    color = BlueDark,
                    textColor = Color.White
                )
            }
        }
    }
}
