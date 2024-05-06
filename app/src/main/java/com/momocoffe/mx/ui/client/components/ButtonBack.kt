package com.momocoffe.mx.ui.client.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.ui.theme.stacionFamily
import com.momocoffe.mx.R


@Composable
fun ButtonBack(
    onclick: () -> Unit,
){

    val modifierCard = Modifier
        .width(240.dp)
        .height(60.dp)
        .border(
            width = 1.2.dp,
            color = Color.White,
            shape = RoundedCornerShape(14.dp)
        ).padding(10.dp)

    Row(
        modifier = modifierCard.clickable { onclick() },
        horizontalArrangement =  Arrangement.Center,
        verticalAlignment =  Alignment.CenterVertically
    ){
        Icon(
            Icons.Rounded.KeyboardArrowLeft,
            contentDescription = stringResource(id = R.string.momo_coffe),
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            "Regresar",
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = stacionFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}