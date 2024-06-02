package com.momocoffe.app.ui.products.components


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.momocoffe.app.ui.theme.stacionFamily
import com.momocoffe.app.R
import com.momocoffe.app.ui.components.SolidLineDivider
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.redhatFamily

data class ItemBox(val id: String, val name: String, val price: Int)
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BoxOptions(
    iconResource: Int,
    textResource: Int,
    onSelectPrice: (Int, String) -> Unit,
    items: List<ItemBox>,
    defaultSelect: String
) {
    val isItemActive = remember { mutableStateOf("") }
    val selectOption = remember { mutableStateOf("") }

    LaunchedEffect(Unit){
        val parts = defaultSelect.split("|")
        for (part in parts) {
            Log.d("Result.ProductsViewModel", items.toString())
            val selected = items.find { it.name == part}
            if (selected != null) {
                onSelectPrice(selected.price, selected.name)
                isItemActive.value = selected.id
                selectOption.value = selected.name
            }
        }

    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(0.3f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = iconResource),
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    tint = Color.White,
                    modifier = Modifier.size(width = 40.dp, height = 40.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    stringResource(id = textResource),
                    fontFamily = stacionFamily,
                    color = Color.White,
                    fontSize = 14.5.sp
                )
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.End
            ) {
                items.forEach { item ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .width(130.dp)
                            .height(55.dp)
                    ) {
                        Button(
                            onClick = {
                                onSelectPrice(item.price, item.name)
                                isItemActive.value = item.id
                                selectOption.value = item.name
                            },
                            modifier = Modifier.fillMaxSize(),
                            shape = RoundedCornerShape(4.dp),
                            border = if (isItemActive.value == item.id) BorderStroke(
                                1.dp,
                                OrangeDark
                            ) else BorderStroke(1.dp, Color.White),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (isItemActive.value == item.id) OrangeDark else BlueDark,
                                disabledBackgroundColor = if (isItemActive.value == item.id) OrangeDark else BlueDark,
                                disabledContentColor = if (isItemActive.value == item.id) OrangeDark else BlueDark,
                            )
                        ) {
                            val txt_price =
                                if (item.price.toInt() == 0) "" else "\n" + "$${item.price}"
                            Text(
                                text = "${item.name} $txt_price",
                                fontSize = 12.sp,
                                color = Color.White,
                                fontFamily = redhatFamily,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                }
            }
        }
        SolidLineDivider()
    }
}