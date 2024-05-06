package com.momocoffe.mx.ui.client.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.momocoffe.mx.ui.theme.OrangeDark
import com.momocoffe.mx.ui.theme.stacionFamily

@Composable
fun ButtonContinue(
    onclick: () -> Unit,
){

    val modifierCard = Modifier
        .width(250.dp)
        .height(50.dp)
        .clip(RoundedCornerShape(14.dp))
        .background(OrangeDark)
        .border(
            width = 1.2.dp,
            color = OrangeDark,
            shape = RoundedCornerShape(14.dp)
        ).padding(10.dp)

    Row(
        modifier = modifierCard.clickable { onclick() },
        horizontalArrangement =  Arrangement.Center,
        verticalAlignment =  Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            "Continuar",
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = stacionFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}