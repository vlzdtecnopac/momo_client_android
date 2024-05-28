package com.momocoffe.app.ui.chekout.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material.icons.Icons
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Clear
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.momocoffe.app.R
import com.momocoffe.app.ui.theme.BlueDark

@Composable
fun OutTextField(
    textValue: String,
    onValueChange: (String) -> Unit,
    onClickButton: () -> Unit,
    keyboardType: KeyboardType,
    onNext: KeyboardActionScope.() -> Unit,
    icon: Painter
) {

    OutlinedTextField(
        value = textValue,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        label = {},
        placeholder = { Text(text = "Invitado", color = BlueDark) },
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = stringResource(id = R.string.momo_coffe),
                tint = BlueDark,
                modifier = Modifier.size(26.dp)
            )
        },
        trailingIcon = {
            IconButton(
                onClick = onClickButton
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    tint = BlueDark,
                    modifier = Modifier.size(26.dp)
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = onNext
        ),
        shape = RoundedCornerShape(20),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlueDark,
            backgroundColor = Color.White,
            cursorColor = BlueDark,
            textColor = BlueDark,
            focusedLabelColor = Color.White,
            unfocusedBorderColor = Color.White,
            trailingIconColor = BlueDark
        ),
        textStyle = TextStyle(
            fontSize = 20.sp
        )
    )

}