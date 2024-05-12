package com.momocoffe.app.ui.orderhere


import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.momocoffe.app.MainActivity
import com.momocoffe.app.ui.orderhere.components.ButtonField
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.orderhere.components.ButtonLang
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.orderhere.components.ButtonEffecty
import com.momocoffe.app.ui.orderhere.components.ContentNotEffecty
import com.momocoffe.app.viewmodel.RegionInternational
import java.util.Locale

@Composable
fun OrderHere(navController: NavController) {
    val context = LocalContext.current
    var isModalVisible by remember { mutableStateOf(false) }
    if (isModalVisible) {
        ContentNotEffecty(navController)
    }
    Column(
        modifier = Modifier.background(BlueDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {}
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(id = R.drawable.logo_momo_white),
                contentDescription = stringResource(id = R.string.momo_coffe),
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            ButtonField(
                text = stringResource(id = R.string.orderhere),
                onclick = {
                    navController.navigate(Destination.Category.route)
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
               Row{
                   ButtonLang(onclick= {
                       val localeToSwitchTo = Locale("es")
                       RegionInternational.updateLocale(context, localeToSwitchTo)
                       val intent = Intent(context, MainActivity::class.java)
                       intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                       context.startActivity(intent)
                   }, text= "Español", icon = painterResource(id = R.drawable.mexico_flag))
                   Spacer(modifier = Modifier.width(15.dp))
                   ButtonLang(onclick= {
                       val localeToSwitchTo = Locale("en")
                       RegionInternational.updateLocale(context, localeToSwitchTo)
                       val intent = Intent(context, MainActivity::class.java)
                       intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                       context.startActivity(intent)
                   }, text="Ingles", icon = painterResource(id = R.drawable.usa_flag))
               }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
                    .padding(20.dp)
            ) {}
            Column(
                horizontalAlignment =  Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
                    .padding(20.dp)
            ) {
                ButtonEffecty(onclick = {isModalVisible = true})
            }
        }
    }
}

