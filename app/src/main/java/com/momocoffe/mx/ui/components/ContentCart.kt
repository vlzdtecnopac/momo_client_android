package com.momocoffe.mx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momocoffe.mx.R
import com.momocoffe.mx.ui.theme.OrangeDark


@Composable
fun ContentCart(onClickOutside: () -> Unit){
    Row{
        Text("Resumen de tu pedido")
        IconButton(
            modifier =  Modifier.background(OrangeDark).border(
                width = 1.2.dp,
                color = Color.White,
                shape = RoundedCornerShape(50.dp)
            ),
            onClick = onClickOutside){
            Icon(
                Icons.Rounded.Close,
                contentDescription = stringResource(id = R.string.momo_coffe),
                tint = Color.White
            )
        };
    }
}


@Composable
fun HeaderCart(){

}