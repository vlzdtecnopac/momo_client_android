package com.momocoffe.app.ui.category

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.momocoffe.app.ui.category.components.BtnOutlineCategory
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.theme.BlueDark

@Preview(widthDp = 1440, heightDp = 875, showBackground = true)
@Composable
fun Category(){
    val list = (1..8).map { it.toString() }
    val navController = rememberNavController();
    Column(){
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
            contentPadding = PaddingValues(vertical = 50.dp, horizontal = 80.dp),
            columns = GridCells.Adaptive(310.dp),
            content = {
                items(list.size) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .padding(10.dp)) {
                        BtnOutlineCategory()
                    }
                }
            }
        )
    }


}