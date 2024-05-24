package com.momocoffe.app.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.momocoffe.app.R
import com.momocoffe.app.ui.category.sections.ColAndHot
import com.momocoffe.app.ui.category.sections.Food
import com.momocoffe.app.ui.category.sections.Store
import com.momocoffe.app.ui.components.cart.Cart
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.stacionFamily
import com.momocoffe.app.viewmodel.CategoryViewModel

@Composable
fun Category(navController: NavController,
             categoryViewModel: CategoryViewModel = viewModel()) {

    var subCategorySelected by rememberSaveable { mutableStateOf(listOf<String>()) }
    var showDialog by remember { mutableStateOf(false) }
    val textLabel = listOf(R.string.coffe, R.string.tea, R.string.coffe_with_tea, R.string.specials_momo, R.string.combos, R.string.foods, R.string.others_drinks, R.string.our_store)

    LaunchedEffect(Unit) {
        categoryViewModel.categorys();
    }

    if (subCategorySelected.isNotEmpty()) {

        if (subCategorySelected.count() <= 2) {
            if (subCategorySelected.contains("Caliente")) {
                ColAndHot(navController, categoryViewModel.selectCategory.value, list = subCategorySelected, onCloseDialog = { showDialog = false })
            } else {
                Store(navController, categoryViewModel.selectCategory.value, list = subCategorySelected, onCloseDialog = { showDialog = false })
            }

        } else {
            Food(navController, categoryViewModel.selectCategory.value, list = subCategorySelected, onCloseDialog = { showDialog = false })
        }

    }


    if (!categoryViewModel.loadingState.value) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            categoryViewModel.categoriesResultState.value?.let { result ->
                result.onSuccess { categoriesResponse ->
                    LazyRow {
                        items(categoriesResponse.size) { index ->
                            Spacer(modifier = Modifier.width(2.dp))
                            BtnOutlineCategory(text = stringResource(id = textLabel[index]), onclick = {
                                if(categoriesResponse[index].subCategory.isNotEmpty()) {
                                    val newSubcategory = categoriesResponse[index].subCategory
                                        .removeSurrounding("[", "]")
                                    val parts = newSubcategory.split(",")
                                    categoryViewModel.selectCategory.value = categoriesResponse[index].nameCategory
                                    subCategorySelected = parts.toList()
                                }else{
                                    navController.navigate("products/${categoriesResponse[index].nameCategory}")
                                }
                            })
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
    onclick: () -> Unit,
    categoryViewModel: CategoryViewModel = viewModel()
) {
    val backgroundCategory = if (categoryViewModel.selectCategory.value == text) OrangeDark else Color.Transparent
    val modifierCard = Modifier
        .width(93.dp)
        .height(60.dp)
        .clip(RoundedCornerShape(14.dp))
        .background(color = backgroundCategory)
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

    ) {
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