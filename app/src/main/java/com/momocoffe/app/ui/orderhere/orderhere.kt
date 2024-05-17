package com.momocoffe.app.ui.orderhere

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.momocoffe.app.App
import com.momocoffe.app.MainActivity
import com.momocoffe.app.ui.orderhere.components.ButtonLang
import com.momocoffe.app.ui.orderhere.components.ButtonField
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.orderhere.components.ButtonEffecty
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.viewmodel.RegionInternational
import com.momocoffe.app.R
import com.momocoffe.app.ui.orderhere.components.ContentNotEffecty
import io.socket.emitter.Emitter
import org.json.JSONObject

@Composable
fun OrderHere(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
    val preference_kiosko_id = sharedPreferences.getString("kioskoId", null) ?: ""
    val preference_shopping_id = sharedPreferences.getString("shoppingId", null) ?: ""

    var isModalVisible by remember { mutableStateOf(false) }

    if(isModalVisible) {
        ContentNotEffecty(navController)
    }

    LaunchedEffect(key1 = true) {
        SocketHandler.getSocket().on("kiosko-verify-socket", Emitter.Listener {
            val payload: JSONObject = it[0] as JSONObject
            val kioskoId = payload.getString("kiosko_id")
            val shoppingId = payload.getString("shopping_id")
            if (preference_shopping_id == shoppingId) {
                if (preference_kiosko_id == kioskoId) {
                    val editor = sharedPreferences.edit()
                    editor.remove("kioskoId")
                    editor.remove("shoppingId")
                    editor.remove("token")
                    editor.apply()

                    val intent = Intent(context, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            }
        })
    }


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
