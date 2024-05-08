package com.momocoffe.mx.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.momocoffe.mx.R
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.ui.theme.OrangeDark
import com.momocoffe.mx.ui.theme.redhatFamily
import com.momocoffe.mx.ui.theme.stacionFamily

@Composable
fun Header(navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlueDark)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Button(
            modifier = Modifier.width(180.dp),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 0.dp, color = Color.Transparent),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            onClick = {
                navController.popBackStack()
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
                Text("Regresar", color = Color.White, fontFamily = redhatFamily, fontSize = 18.sp)
            }

        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.momo_coffe),
            modifier = Modifier.width(135.dp)
        )
        Row {
            Column {
                Text(
                    "Bienvenido",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = stacionFamily,
                    color = Color.White
                )
                Text(
                    "vlzdavid12@outlook.com",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = redhatFamily,
                    color = Color.White
                )
            }
            Image(
                painter = painterResource(R.drawable.header_icon_momo),
                contentDescription = stringResource(id = R.string.momo_coffe),
                modifier = Modifier.width(85.dp)
            )

        }

    }
}