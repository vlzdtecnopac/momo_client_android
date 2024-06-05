package com.momocoffe.app.ui.category


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.momocoffe.app.ui.category.components.BtnOutlineCategory
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.category.sections.ColAndHot
import com.momocoffe.app.ui.category.sections.Food
import com.momocoffe.app.ui.category.sections.Store
import com.momocoffe.app.viewmodel.CategoryViewModel
import com.momocoffe.app.R
import com.momocoffe.app.ui.theme.BlueDarkTransparent
import com.spr.jetpack_loading.components.indicators.BallClipRotatePulseIndicator

data class ListItem(val iconResId: Int, val name: String)

@Composable
fun Category(navController: NavController,
             categoryViewModel: CategoryViewModel = viewModel()){
    val loading = categoryViewModel.loadingState.value
    var subCategorySelected by rememberSaveable { mutableStateOf(listOf<String>()) }
    var showDialog by remember { mutableStateOf(false) }
    var selectCategory by remember { mutableStateOf(value = "" ) }
    val textLabel = listOf(R.string.coffe, R.string.tea, R.string.coffe_with_tea, R.string.specials_momo, R.string.combos, R.string.foods, R.string.others_drinks, R.string.our_store)

    LaunchedEffect(Unit){
        categoryViewModel.categorys();
    }

    if (subCategorySelected.isNotEmpty() && showDialog) {
        if (subCategorySelected.count() <= 2) {
            ColAndHot(navController, selectCategory, list = subCategorySelected, onCloseDialog = { showDialog = false })
        } else {
            if(selectCategory == "tienda"){
                Store(navController, selectCategory, list = subCategorySelected, onCloseDialog = { showDialog = false })
            }else {
                Food(
                    navController,
                    selectCategory,
                    list = subCategorySelected,
                    onCloseDialog = { showDialog = false })
            }
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlueDark),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Header(navController, buttonExit = true)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueDark),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                categoryViewModel.categoriesResultState.value?.let { result ->
                    result.onSuccess { categoriesResponse ->
                        LazyVerticalGrid(
                            horizontalArrangement = Arrangement.Center,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .widthIn(0.dp, 900.dp),
                            contentPadding = PaddingValues(vertical = 20.dp, horizontal = 30.dp),
                            columns = GridCells.Fixed(4),
                            content = {
                                items(categoriesResponse.size) { index ->
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(14.dp))
                                            .padding(5.dp)
                                    ) {
                                        BtnOutlineCategory(
                                            icon = when (categoriesResponse[index].classIcon) {
                                                "coffee-icon" -> R.drawable.coffee_mug_icon
                                                "tea-icon" -> R.drawable.tea_mug_icon
                                                "coffee-tea-icon" -> R.drawable.coffee_tea_icon
                                                "specials-icon" -> R.drawable.specials_momo_icon
                                                "combos-icon" -> R.drawable.combos_icon
                                                "food-icon" -> R.drawable.alimentos_icon
                                                "drinks-icon" -> R.drawable.other_drinks_icon
                                                "store-icon" -> R.drawable.our_store_icon
                                                else -> R.drawable.logo
                                            },
                                            text = stringResource(id = textLabel[index]),
                                            onclick = {
                                                if (categoriesResponse[index].subCategory.isNotEmpty()) {
                                                    val newSubcategory =
                                                        categoriesResponse[index].subCategory
                                                            .removeSurrounding("[", "]")
                                                    val parts = newSubcategory.split(",")
                                                    subCategorySelected = parts.toList()
                                                    selectCategory =
                                                        categoriesResponse[index].nameCategory
                                                    showDialog = true
                                                } else {
                                                    navController.navigate("products/${categoriesResponse[index].nameCategory}")
                                                }

                                            }
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
        if (loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueDarkTransparent)
            ) {
                BallClipRotatePulseIndicator()
            }
        }
    }


}