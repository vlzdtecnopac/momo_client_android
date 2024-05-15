package com.momocoffe.app.ui.products.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.redhatFamily

data class ItemBox(val id: Int, val name: String, val price: Int)
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BoxOptions(
    iconResource: Int,
    textResource: Int,
    items: List<ItemBox>
) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            modifier = Modifier.weight(0.3f),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painterResource(id = iconResource),
                contentDescription = stringResource(id = R.string.momo_coffe),
                tint = Color.White,
                modifier = Modifier.size(width = 40.dp, height = 40.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
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
        ){
            items.forEach { item ->
                Box(
                    modifier = Modifier.padding(2.dp)
                ){
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .width(134.dp).height(60.dp),
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(1.dp, Color.White),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = BlueDark,
                            disabledBackgroundColor = BlueDark,
                            disabledContentColor = BlueDark,
                        )
                    ) {
                        Text(
                            text = item.name,
                            fontSize = 14.sp,
                            color = Color.White,
                            fontFamily = redhatFamily,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }
    }
}