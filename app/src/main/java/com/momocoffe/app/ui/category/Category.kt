package com.momocoffe.app.ui.category


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.momocoffe.app.ui.category.components.BtnOutlineCategory
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.theme.BlueLight

data class ListItem(val iconResId: Int, val name: String)

@Composable
fun Category(navController: NavController){

    val jsonList = listOf(
        ListItem(R.drawable.coffee_mug_icon, stringResource(id = R.string.coffe)),
        ListItem(R.drawable.tea_mug_icon,  stringResource(id = R.string.tea)),
        ListItem(R.drawable.coffee_tea_icon,  stringResource(id = R.string.coffe_with_tea)),
        ListItem(R.drawable.specials_momo_icon,  stringResource(id = R.string.specials_momo)),
        ListItem(R.drawable.combos_icon,  stringResource(id = R.string.combos)),
        ListItem(R.drawable.alimentos_icon, stringResource(id = R.string.foods)),
        ListItem(R.drawable.other_drinks_icon, stringResource(id = R.string.others_drinks)),
        ListItem(R.drawable.our_store_icon, stringResource(id = R.string.our_store)),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueDark),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(navController)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Column(
            modifier = Modifier.fillMaxSize().background(BlueDark),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            LazyVerticalGrid(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .widthIn(0.dp, 900.dp),
                contentPadding = PaddingValues(vertical = 20.dp, horizontal = 30.dp),
                columns = GridCells.Fixed(4),
                content = {
                    items(jsonList) { item ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .padding(5.dp)
                        ) {
                            BtnOutlineCategory(
                                icon = item.iconResId,
                                text = item.name,
                                onclick = {
                                    navController.navigate(Destination.Products.route)
                                }
                            )
                        }
                    }
                }
            )
        }
    }


}