package com.momocoffe.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.redhatFamily

@Composable
fun Header(navController: NavController, buttonExit: Boolean) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    var textButton =
        if (buttonExit) stringResource(id = R.string.txt_exit) else stringResource(id = R.string.txt_back)

    if (navBackStackEntry.value?.destination?.route?.startsWith("checkout") == true) {
        textButton = stringResource(id = R.string.txt_back)
    }

    VerifyKiosko(navController)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlueDark)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Button(
            modifier = Modifier
                .width(180.dp)
                .border(0.dp, Color.Transparent, shape = RoundedCornerShape(10.dp)),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 0.dp, color = Color.Transparent),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            onClick = {
                if (navBackStackEntry.value?.destination?.route?.startsWith("checkout") == true) {
                    navController.navigate(Destination.Category.route)
                } else {
                    if (buttonExit) {
                        navController.navigate(Destination.OrderHere.route)
                    } else {
                        navController.popBackStack()
                    }
                }
            },
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    tint = Color.White,
                    modifier = Modifier.size(width = 18.dp, height = 18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    textButton,
                    color = Color.White,
                    fontFamily = redhatFamily,
                    fontSize = 18.sp
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.momo_coffe),
                modifier = Modifier.width(135.dp)
            )
        }

        Sidebar(navController)
    }
}