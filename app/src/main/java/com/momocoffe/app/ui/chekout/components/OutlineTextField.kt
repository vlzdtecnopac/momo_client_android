package com.momocoffe.app.ui.chekout.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.momocoffe.app.R
import com.momocoffe.app.ui.theme.redhatFamily

@Composable
fun OutlineTextField(
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    textValue: String,
    icon: Int?,
    onValueChange: (String) -> Unit,
    onClickButton: () -> Unit,
    onNext: (KeyboardActionScope.() -> Unit),
    borderColor: Color
){

    OutlinedTextField(
        value = textValue,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder, fontSize = 12.sp, fontFamily = redhatFamily) },
        leadingIcon = {
            icon?.let { painterResource(it) }?.let {
                Icon(
                    modifier = Modifier.size(20.dp).padding(0.dp),
                    painter = it,
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    tint = borderColor
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = onClickButton
            ) {
                Icon(
                    modifier = Modifier.size(20.dp).padding(0.dp),
                    imageVector = Icons.Filled.Clear,
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    tint = borderColor
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
            focusedBorderColor = borderColor,
            cursorColor = borderColor,
            textColor = borderColor,
            focusedLabelColor = borderColor,
            unfocusedBorderColor = borderColor,
            placeholderColor = borderColor,
            unfocusedLabelColor = borderColor

        )
    )
}