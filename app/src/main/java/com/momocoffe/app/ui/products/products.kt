package com.momocoffe.app.ui.products

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.momocoffe.app.ui.components.CardProduct
import com.momocoffe.app.ui.components.Category
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight

@Composable
fun Products(navController: NavHostController, category: String? = "", subcategory: String? = ""){

    Log.d("Result.CategoryViewModel", category.toString())
    //Log.d("Result.CategoryViewModel", subcategory.toString())
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
            Header(navController)
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