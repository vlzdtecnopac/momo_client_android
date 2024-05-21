package com.momocoffe.app.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.momocoffe.app.ui.components.cart.Cart
import com.momocoffe.app.ui.theme.stacionFamily
import com.momocoffe.app.viewmodel.CategoryViewModel

@Composable
fun Category(navController: NavController, categoryViewModel: CategoryViewModel = viewModel()){
    LaunchedEffect(Unit){
        categoryViewModel.categorys();
    }


    if(!categoryViewModel.loadingState.value) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            categoryViewModel.categoriesResultState.value?.let { result ->
                result.onSuccess { categoriesResponse ->
                    LazyRow {
                        items(categoriesResponse) { category ->
                            Spacer(modifier = Modifier.width(5.dp))
                            BtnOutlineCategory(text = category.nameCategory, onclick = {})
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                }
            }




            Cart(navController)
        }
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