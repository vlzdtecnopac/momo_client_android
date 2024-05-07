package com.momocoffe.mx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.ui.theme.stacionFamily

@Composable
fun Category(){
    Row{
        BtnOutlineCategory(text = "Café", onclick = {} )
        Spacer(modifier = Modifier.width(5.dp))
        BtnOutlineCategory(text = "Té", onclick = {} )
        Spacer(modifier = Modifier.width(5.dp))
        BtnOutlineCategory(text = "Café con Té", onclick = {} )
        Spacer(modifier = Modifier.width(5.dp))
        BtnOutlineCategory(text = "Especiales Momo", onclick = {} )
        Spacer(modifier = Modifier.width(5.dp))
        BtnOutlineCategory(text = "Otras Bebidas", onclick = {} )
        Spacer(modifier = Modifier.width(5.dp))
        BtnOutlineCategory(text = "Alimentos", onclick = {} )
        Spacer(modifier = Modifier.width(5.dp))
        BtnOutlineCategory(text = "Combos", onclick = {} )
        Spacer(modifier = Modifier.width(5.dp))
        BtnOutlineCategory(text = "Nuestra Tienda", onclick = {} )
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
        .padding(10.dp)

    Column(
        modifier = modifierCard.clickable { onclick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Text(
            text,
            color = Color.White,
            fontSize = 15.sp,
            fontFamily = stacionFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}