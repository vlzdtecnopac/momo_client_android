package com.momocoffe.mx.ui.client.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.momocoffe.mx.R
import com.momocoffe.mx.ui.theme.BlueDark

@Composable
fun DropDownOutline() {
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val items = listOf("+55", "+57")

    val selectModifierCard =
        Modifier
            .fillMaxWidth()
            .height(55.dp)
            .clickable(onClick = { expanded = true })
            .padding(start = 20.dp)
            .background(BlueDark)
            .border(
                width = 1.1.dp,
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )

    Row(
        modifier = selectModifierCard,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        Text(items[selectedIndex], color = Color.White)
        Icon(
            Icons.Rounded.KeyboardArrowDown,
            contentDescription = stringResource(id = R.string.momo_coffe),
            tint = Color.White
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .width(180.dp)
            .background(
                Color.White
            )
    ) {
        items.forEachIndexed { index, s ->
            DropdownMenuItem(onClick = {
                selectedIndex = index
                expanded = false
            }) {
                Text(text = s )
            }
        }
    }
}