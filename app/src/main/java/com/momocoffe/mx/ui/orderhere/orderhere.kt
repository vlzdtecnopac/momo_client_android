package com.momocoffe.mx.ui.orderhere


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.momocoffe.mx.ui.orderhere.components.ButtonField
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.ui.orderhere.components.ButtonLang
import com.momocoffe.mx.R
import com.momocoffe.mx.navigation.Destination
import com.momocoffe.mx.ui.orderhere.components.ButtonEffecty
import com.momocoffe.mx.ui.orderhere.components.ContentNotEffecty

@Composable
fun OrderHere(navController: NavController) {
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
               Row{
                   ButtonLang(onclick= {}, text= "Español", icon = painterResource(id = R.drawable.mexico_flag))
                   Spacer(modifier = Modifier.width(15.dp))
                   ButtonLang(onclick= {}, text="Ingles", icon = painterResource(id = R.drawable.usa_flag))
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

