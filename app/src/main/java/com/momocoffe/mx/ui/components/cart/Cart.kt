package com.momocoffe.mx.ui.components.cart
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.momocoffe.mx.R
import com.momocoffe.mx.ui.theme.OrangeDark
import com.momocoffe.mx.ui.theme.stacionFamily
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.momocoffe.mx.ui.theme.BlueDarkTransparent

@Composable
fun Cart() {
    var showPopup by rememberSaveable {
        mutableStateOf(false)
    }

    Spacer(modifier = Modifier.width(10.dp))
    Box {
        BtnCartMomo(text = "Ver Carrito", onclick = {
            showPopup = true
        }, icon = R.drawable.cart_icon)

        if (showPopup) {
            PopupBox( onClickOutside = { showPopup = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight()
                        .background(Color.White)
                        .align(Alignment.TopEnd)
                        .zIndex(40F),
                ) {
                    ContentCart(onClickOutside = { showPopup = false })
                }
            }
        }
    }
}

@Composable
fun PopupBox(onClickOutside: () -> Unit, content: @Composable () -> Unit) {
    // full screen background
    Dialog(
        onDismissRequest = {},
        DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BlueDarkTransparent)
                .zIndex(10F),
        ) {
            content()
        }
    }
}


@Composable
fun BtnCartMomo(
    text: String,
    icon: Int,
    onclick: () -> Unit
) {
    val modifierCard = Modifier
        .width(130.dp)
        .height(45.dp)
        .clip(RoundedCornerShape(14.dp))
        .background(OrangeDark)
        .border(
            width = 1.2.dp,
            color = OrangeDark,
            shape = RoundedCornerShape(14.dp)
        )
        .padding(8.dp)

    Row(
        modifier = modifierCard.clickable { onclick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(id = icon),
            contentDescription = stringResource(id = R.string.momo_coffe),
            tint = Color.White,
            modifier = Modifier.size(width = 30.dp, height = 30.dp)
        )
        Text(
            text,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = stacionFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}
