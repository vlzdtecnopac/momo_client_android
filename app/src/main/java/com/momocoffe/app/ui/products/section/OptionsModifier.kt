package com.momocoffe.app.ui.products.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.momocoffe.app.R
import com.momocoffe.app.ui.products.components.BoxOptions
import com.momocoffe.app.ui.products.components.ItemBox
import com.momocoffe.app.ui.products.components.ItemList
import com.momocoffe.app.ui.products.components.ListOptions


@Composable
fun OptionsModifier() {
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(100) }

    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .verticalScroll(state)
    ) {
        BoxOptions(
            iconResource = R.drawable.tamano_icon,
            textResource = R.string.size,
            items = listOf(
                ItemBox(1, "Chico 12 Oz", 10),
                ItemBox(2, "Grande 16 Oz", 10),
            )
        )
        BoxOptions(
            iconResource = R.drawable.tamano_icon,
            textResource = R.string.size,
            items = listOf(
                ItemBox(1, "Chico 12 Oz", 10),
                ItemBox(2, "Grande 16 Oz", 10),
            )
        )
        BoxOptions(
            iconResource = R.drawable.tamano_icon,
            textResource = R.string.size,
            items = listOf(
                ItemBox(1, "Chico 12 Oz", 10),
                ItemBox(2, "Grande 16 Oz", 10),
            )
        )
        ListOptions(iconResource = R.drawable.tamano_icon,  items = listOf(
            ItemList(1, "Extra shot de café",  10),
            ItemList(1, "Extra shot de café",  10),
        ))
        ListOptions(iconResource = R.drawable.tamano_icon,  items = listOf(
            ItemList(1, "Con Tapa",  10),
            ItemList(1, "Sin Tapa",  10),
        ))
    }
}