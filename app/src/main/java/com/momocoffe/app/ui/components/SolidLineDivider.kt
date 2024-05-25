package com.momocoffe.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp

@Composable
fun SolidLineDivider(
    color: Color = Color.White,
    thickness: Dp = .8.dp,
    paddingTop: Dp = 8.dp,
    paddingButton: Dp = 0.dp,
    paddingLeft: Dp = 0.dp,
    paddingRight: Dp = 0.dp
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(
                top = paddingTop,
                bottom = paddingButton,
                end = paddingRight,
                start = paddingLeft
            )
            .height(thickness)
            .background(color)
    )
}