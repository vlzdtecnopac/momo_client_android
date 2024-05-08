package com.momocoffe.mx.ui.chekout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.momocoffe.mx.ui.components.Category
import com.momocoffe.mx.ui.components.Header
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.ui.theme.BlueLight

@Composable
fun Checkout(navController: NavController){

    Column(modifier = Modifier
        .background(BlueLight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueDark),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(navController)
            Category(navController)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}