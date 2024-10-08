package com.momocoffe.app.ui.category.sections
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.momocoffe.app.ui.category.components.BtnOutlineCategory
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.R
import com.momocoffe.app.ui.category.ListItem
import com.momocoffe.app.ui.components.Header


@Composable
fun Food(navController: NavController,
         selectCategory: String,
         list: List<String>,
         onCloseDialog: () -> Unit){

    val image = listOf(R.drawable.sweet_icon, R.drawable.bread_icon, R.drawable.snack_icon)
    val textLabel = listOf(R.string.text_sweet, R.string.text_sald, R.string.text_snack)

    val newList = mutableListOf<ListItem>()

    list.forEachIndexed { index, item ->
        newList.add(ListItem(image[index], stringResource(id = textLabel[index])))
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
            Header(navController = navController, buttonExit = false)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LazyVerticalGrid(
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .widthIn(0.dp, 800.dp),
                    contentPadding = PaddingValues(vertical = 20.dp, horizontal = 20.dp),
                    columns = GridCells.Fixed(3),
                    content = {
                        items(newList.size) { index ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .padding(20.dp)
                            ) {
                                BtnOutlineCategory(
                                    icon = newList[index].iconResId,
                                    text = newList[index].name,
                                    onclick = {
                                        navController.navigate("products/$selectCategory/${list[index]}")

                                    }
                                )
                            }
                        }
                    })
            }
        }

    }
}