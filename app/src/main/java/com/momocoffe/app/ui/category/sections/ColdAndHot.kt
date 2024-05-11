package com.momocoffe.app.ui.category.sections

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.momocoffe.app.R
import com.momocoffe.app.ui.category.ListItem
import com.momocoffe.app.ui.category.components.BtnOutlineCategory
import com.momocoffe.app.ui.theme.BlueDark


@Preview(widthDp = 1440, heightDp = 875, showBackground = true)
@Composable
fun ColAndHot() {

    val jsonList = listOf(
        ListItem(R.drawable.hot_icon, "Caliente"),
        ListItem(R.drawable.cold_icon, "Frio"),
    )

    Dialog(
        onDismissRequest = {},
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
                    items(jsonList) { item ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .padding(20.dp)
                        ) {
                            BtnOutlineCategory(
                                icon = item.iconResId,
                                text = item.name,
                                onclick = {}
                            )
                        }
                    }
                })
        }
        }

    }
}