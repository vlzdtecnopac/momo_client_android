package com.momocoffe.app.ui.chekout.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.chekout.components.OutTextField
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.BlueLight
import com.momocoffe.app.ui.theme.redhatFamily

@Composable
fun ContentTypePayment() {
    var invite by remember { mutableStateOf(value = "") }
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutTextField(
            textValue = invite,
            onValueChange = { invite = it },
            onClickButton = { invite = "" },
            text = stringResource(id = R.string.invited),
            keyboardType = KeyboardType.Text,
            icon = painterResource(R.drawable.user),
            onNext = {
                focusManager.moveFocus(
                    FocusDirection.Down
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Seleccione tu método de pago",
            color = Color.White,
            fontSize = 22.sp,
            fontFamily = redhatFamily,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(60.dp)
                .padding(horizontal = 5.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BlueLight,
                disabledBackgroundColor = BlueLight,
                disabledContentColor = BlueLight
            )
        ) {
           Text(
                text = stringResource(id = R.string.not_want),
                fontSize = 18.sp,
                color = BlueDark,
                fontFamily = redhatFamily,
                fontWeight = FontWeight(700)
            )
        }
    }
}