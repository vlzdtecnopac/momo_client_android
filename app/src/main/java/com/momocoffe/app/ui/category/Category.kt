package com.momocoffe.app.ui.category


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.momocoffe.app.R
import com.momocoffe.app.ui.category.components.BtnOutlineCategory
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.theme.BlueDark
data class ListItem(val iconResId: Int, val name: String)
@Preview(widthDp = 1440, heightDp = 875, showBackground = true)
@Composable
fun Category(){

    val navController = rememberNavController();

    val jsonList = listOf(
        ListItem(R.drawable.coffee_mug_icon, "Café"),
        ListItem(R.drawable.tea_mug_icon, "Té"),
        ListItem(R.drawable.coffee_tea_icon, "Café con Té"),
        ListItem(R.drawable.specials_momo_icon, "Especiales MoMo"),
        ListItem(R.drawable.combos_icon, "Combos"),
        ListItem(R.drawable.alimentos_icon, "Alimentos"),
        ListItem(R.drawable.other_drinks_icon, "Otras Bebidas"),
        ListItem(R.drawable.our_store_icon, "Nuestra Tienda"),
    )


    Column{
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueDark),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(navController)
            Spacer(modifier = Modifier.height(8.dp))
        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 40.dp, horizontal = 60.dp),
            columns = GridCells.Fixed(4),
            content = {
                items(jsonList) {  item ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .padding(5.dp)) {
                        BtnOutlineCategory(
                            icon= item.iconResId,
                            text = item.name,
                            onclick = {}
                        )
                    }
                }
            }
        )
    }


}