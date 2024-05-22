package com.momocoffe.app.ui.category.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.category.ListItem
import com.momocoffe.app.ui.category.components.BtnOutlineCategory

import com.momocoffe.app.ui.theme.BlueDark



@Composable
fun Store(navController: NavController,
          list: List<String>,
          onCloseDialog: () -> Unit){

    val image = listOf(R.drawable.metch_icon, R.drawable.coffe_granel_icon)
    val textLabel = listOf(R.string.text_merch, R.string.text_coffe_granel)
    val newList = mutableListOf<ListItem>()

    list.forEachIndexed { index, item ->
        newList.add(ListItem(image[index], stringResource(id =  textLabel[index])))
    }


    Dialog(
        onDismissRequest = onCloseDialog,
        DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .padding(0.dp)
                .zIndex(88f),
            color = BlueDark
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LazyVerticalGrid(
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .widthIn(0.dp, 600.dp),
                    contentPadding = PaddingValues(vertical = 20.dp, horizontal = 20.dp),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(newList) { item ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .padding(20.dp)
                            ) {
                                BtnOutlineCategory(
                                    icon = item.iconResId,
                                    text = item.name,
                                    onclick = {
                                        navController.navigate("products/myCategory/${item.name}")
                                    }
                                )
                            }
                        }
                    })
            }
        }

    }

}