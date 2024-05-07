package com.momocoffe.mx.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import retrofit2.http.Header


@Composable
fun ContentCart(){
    HeaderCart()
}


@Composable
fun HeaderCart(){
    Row(){
        Text("Resumen de tu pedido")
    }
}