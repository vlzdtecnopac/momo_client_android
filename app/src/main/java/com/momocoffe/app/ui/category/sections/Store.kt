package com.momocoffe.app.ui.category.sections

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex

import com.momocoffe.app.ui.theme.BlueDark


@Preview(widthDp = 1440, heightDp = 875, showBackground = true)
@Composable
fun Store(){
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

        }
    }

}