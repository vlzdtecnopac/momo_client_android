package com.momocoffe.mx.ui.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.momocoffe.mx.R
import com.momocoffe.mx.ui.components.cart.CardProduct
import com.momocoffe.mx.ui.components.Category
import com.momocoffe.mx.ui.components.Header
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.ui.theme.BlueLight

@Composable
fun Products(navController: NavHostController){
    val list = (1..100).map { it.toString() }

    Column(modifier = Modifier
        .background(BlueLight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueDark),
            horizontalAlignment = Alignment.CenterHorizontally){
            Header()
            Category(navController)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.fillMaxWidth(0.9f)) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            content = {
                items(list.size) { index ->
                  CardProduct(navController)
                }
            }
        )
    }
    }


}