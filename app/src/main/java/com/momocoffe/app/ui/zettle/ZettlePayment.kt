package com.momocoffe.app.ui.zettle

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.ui.theme.stacionFamily
import com.zettle.sdk.ZettleSDK
import com.zettle.sdk.core.auth.User

@Composable
fun ZettlePayment(navController: NavHostController){
    val context = LocalContext.current;
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(BlueDark)
    ) {
        Image(
            painter = painterResource(id = R.drawable.izettle_logo),
            contentDescription = stringResource(id = R.string.momo_coffe),
            modifier = Modifier.width(190.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        AuthenticationStatusText()
        Spacer(modifier = Modifier.height(10.dp))
        Text("Antes de comenzar conectante con el sistema de pagos de Zettle.", color = Color.White, fontFamily = redhatFamily, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                val activity = context as? Activity
                activity?.let {
                    ZettleSDK.instance?.login(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(horizontal = 5.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = OrangeDark,
                disabledBackgroundColor = OrangeDark,
                disabledContentColor = OrangeDark
            )
        ) {
            Text(
                text = "Ingresar",
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = redhatFamily,
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
                navController.navigate(Destination.Login.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(horizontal = 5.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BlueLight,
                disabledBackgroundColor = BlueLight,
                disabledContentColor = BlueLight
            )
        ) {
            Text(
                text = "No Deseo",
                fontSize = 16.sp,
                color = BlueDark,
                fontFamily = redhatFamily,
                fontWeight = FontWeight(700)
            )
        }
    }

}


@Composable
fun AuthenticationStatusText() {
    val lifecycleOwner = LocalLifecycleOwner.current

    var authStatus by rememberSaveable { mutableStateOf("Not authenticated") }

    val authObserver: (User.AuthState) -> Unit = { state ->
        authStatus = when (state) {
            is User.AuthState.LoggedIn -> "User authenticated"
            is User.AuthState.LoggedOut -> "No authenticated user"
            else -> "Conectando.."
        }
    }

    LaunchedEffect(Unit) {
        ZettleSDK.instance?.authState?.observe(lifecycleOwner, authObserver)
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = authStatus, color = Color.White, fontFamily = stacionFamily, fontSize = 28.sp)
}