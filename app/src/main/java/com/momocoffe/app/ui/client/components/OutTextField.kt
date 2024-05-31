package com.momocoffe.app.ui.client.components

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.momocoffe.app.R

@Composable
fun OutTextField(
    textValue: String,
    onValueChange: (String) -> Unit,
    onClickButton: () -> Unit,
    text: String,
    keyboardType: KeyboardType,
    onDone: () -> Unit,
    icon: Painter
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = textValue,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        label = { Text(text, color = Color.White) },
        placeholder = { Text(text = "santiago@mail.com", color = Color.White) },
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = stringResource(id = R.string.momo_coffe),
                tint = Color.White,
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
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
                focusManager.clearFocus()
            }
        ),
        shape = RoundedCornerShape(20),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            cursorColor = Color.White,
            textColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedBorderColor = Color.White
        )
    )

}