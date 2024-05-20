package com.momocoffe.app.ui.orderhere

import android.content.Context
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.momocoffe.app.ui.orderhere.components.ButtonLang
import com.momocoffe.app.ui.orderhere.components.ButtonField
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.orderhere.components.ButtonEffecty
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.viewmodel.RegionInternational
import com.momocoffe.app.R
import com.momocoffe.app.network.repository.SessionManager
import com.momocoffe.app.ui.components.VerifyKiosko
import com.momocoffe.app.ui.orderhere.components.ContentNotEffecty
import com.momocoffe.app.viewmodel.KioskoViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

@Composable
fun OrderHere(navController: NavController,) {
    val context = LocalContext.current

    var isModalVisible by remember { mutableStateOf(false) }


    if(isModalVisible) {
        ContentNotEffecty(navController)
    }

    VerifyKiosko(navController)

    Column(
        modifier = Modifier.background(BlueDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {}
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_momo_white),
                contentDescription = stringResource(id = R.string.momo_coffe),
                modifier = Modifier.width(350.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            ButtonField(
                text = stringResource(id = R.string.orderhere),
                onclick = {
                    navController.navigate(Destination.Client.route)
                },
                enabled = true
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
                    .padding(20.dp)
            ) {
                Row {
                    ButtonLang(
                        onclick = {
                            RegionInternational.setLocale(context, "es")
                        },
                        text = stringResource(id = R.string.lang_es),
                        icon = painterResource(id = R.drawable.mexico_flag)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    ButtonLang(
                        onclick = {
                            RegionInternational.setLocale(context, "en")
                        },
                        text = stringResource(id = R.string.lang_en),
                        icon = painterResource(id = R.drawable.usa_flag)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
                    .padding(20.dp)
            ) {}
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
                    .padding(20.dp)
            ) {
                ButtonEffecty(onclick = { isModalVisible = true })
            }
        }
    }
}
