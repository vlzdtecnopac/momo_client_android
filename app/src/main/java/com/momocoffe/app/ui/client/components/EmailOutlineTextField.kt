package com.momocoffe.app.ui.client.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.momocoffe.app.viewmodel.ClientViewModel
import kotlinx.coroutines.delay
import com.momocoffe.app.R

private const val DEBOUNCE_TIME = 500L

@Composable
fun EmailOutlineTextField(
    clientViewModel: ClientViewModel = viewModel(),
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    icon: Int,
    onClickButton: () -> Unit,
    onNext: (KeyboardActionScope.() -> Unit),
    onEmailValidated: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    val (isValid, setIsValid) = remember { mutableStateOf(false) }

    LaunchedEffect(email) {
        delay(DEBOUNCE_TIME)
        setIsValid(isValidEmail(context, clientViewModel, email))
        onEmailValidated(isValid)
    }

    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            Icon(
                painter = painterResource(icon),
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
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = onNext
        ),
        shape = RoundedCornerShape(20),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            cursorColor = Color.White,
            textColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedBorderColor = Color.White,
            placeholderColor = Color.White,
            unfocusedLabelColor = Color.White

        )
    )
}

private suspend fun isValidEmail(
    context: Context,
    clientViewModel: ClientViewModel,
    email: String
): Boolean{
    clientViewModel.getClient(email)
    delay(2000)
    clientViewModel.clientResultCheckEmailState.value?.let { result ->
        when {
            result.isSuccess -> {
                val response = result.getOrThrow()
                if(response.items.isNotEmpty()){
                    Toast.makeText(context, "Este correo se encuentra registrado.", Toast.LENGTH_LONG)
                        .show()
                    return true
                }else{
                    return false
                }
            }

            result.isFailure -> {
                val exception = result.exceptionOrNull()
                Log.e("Result.ClientViewModel", exception.toString())
            }

            else -> {}
        }

    }
    return false
}