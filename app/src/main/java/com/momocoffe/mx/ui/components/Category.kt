package com.momocoffe.mx.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.momocoffe.mx.ui.theme.stacionFamily

@Composable
fun Category(){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BtnOutlineCategory(text = "Café", onclick = {})
            Spacer(modifier = Modifier.width(5.dp))
            BtnOutlineCategory(text = "Té", onclick = {})
            Spacer(modifier = Modifier.width(5.dp))
            BtnOutlineCategory(text = "Café con Té", onclick = {})
            Spacer(modifier = Modifier.width(5.dp))
            BtnOutlineCategory(text = "Especiales Momo", onclick = {})
            Spacer(modifier = Modifier.width(5.dp))
            BtnOutlineCategory(text = "Otras Bebidas", onclick = {})
            Spacer(modifier = Modifier.width(5.dp))
            BtnOutlineCategory(text = "Alimentos", onclick = {})
            Spacer(modifier = Modifier.width(5.dp))
            BtnOutlineCategory(text = "Combos", onclick = {})
            Spacer(modifier = Modifier.width(5.dp))
            BtnOutlineCategory(text = "Nuestra Tienda", onclick = {})
            Spacer(modifier = Modifier.width(10.dp))
            Cart()
        }
}


@Composable
fun BtnOutlineCategory(
    text: String,
    onclick: () -> Unit
){
    val modifierCard = Modifier
        .width(93.dp)
        .height(60.dp)
        .border(
            width = 1.2.dp,
            color = Color.White,
            shape = RoundedCornerShape(14.dp)
        )
        .padding(8.dp)

    Column(
        modifier = modifierCard.clickable { onclick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Text(
            text,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = stacionFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}