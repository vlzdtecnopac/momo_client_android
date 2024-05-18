package com.momocoffe.app.ui.client.section


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.momocoffe.app.ui.theme.*
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.network.dto.ClientRequest
import com.momocoffe.app.ui.client.components.*
import com.momocoffe.app.viewmodel.ClientViewModel
import com.spr.jetpack_loading.components.indicators.BallClipRotatePulseIndicator


@Composable
fun RegisterClient(navController: NavController, clientViewModel: ClientViewModel = viewModel()) {
    val context = LocalContext.current
    val loading = clientViewModel.loadingState.value;
    val focusManager = LocalFocusManager.current

    var firstName by remember { mutableStateOf(value = "") }
    var lastName by remember { mutableStateOf(value = "") }
    var phone by remember { mutableStateOf(value = "") }
    val selected = remember { mutableStateOf(value = "") }
    val selectedLabel = remember { mutableStateOf(value = "") }
    val checkedState = remember { mutableStateOf(true) }
    val email = remember { mutableStateOf(value = "") }

    val isValidate by derivedStateOf { email.value.isNotBlank() && firstName.isNotBlank() && lastName.isNotBlank()  && phone.isNotBlank()  }

    LaunchedEffect(clientViewModel.clientResultState.value) {
        clientViewModel.clientResultState.value?.let { result ->
            when {
                result.isSuccess -> {
                    Toast.makeText(context, "Se ha creado nuevo cliente.", Toast.LENGTH_LONG)
                        .show()
                    navController.navigate(Destination.Category.route)
                    clientViewModel.clientResultState.value = null
                }
                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Toast.makeText(context, "Error en el registro del cliente.", Toast.LENGTH_LONG)
                        .show()
                    Log.d("Result.ViewModel", exception.toString())
                }
            }
        }
    }


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
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BlueDark),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = stringResource(id = R.string.momo_coffe),
                        modifier = Modifier.width(180.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        stringResource(id = R.string.register),
                        color = Color.White,
                        fontSize = 22.sp,
                        fontFamily = redhatFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(modifier = Modifier.width(520.dp)) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    stringResource(id = R.string.enter_data_person),
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontFamily = stacionFamily,
                                    fontWeight = FontWeight.Normal
                                )
                                OutlineTextField(
                                    label = stringResource(id = R.string.first_name),
                                    placeholder = stringResource(id = R.string.first_name),
                                    icon = R.drawable.user,
                                    keyboardType = KeyboardType.Text,
                                    textValue = firstName,
                                    onValueChange = { firstName = it },
                                    onClickButton = { firstName = "" },
                                    onNext = {
                                        focusManager.moveFocus(
                                            FocusDirection.Down
                                        )
                                    })
                                OutlineTextField(
                                    label = stringResource(id = R.string.last_name),
                                    placeholder = stringResource(id = R.string.last_name),
                                    icon = R.drawable.user,
                                    keyboardType = KeyboardType.Text,
                                    textValue = lastName,
                                    onValueChange = { lastName = it },
                                    onClickButton = { lastName = "" },
                                    onNext = {
                                        focusManager.moveFocus(
                                            FocusDirection.Down
                                        )
                                    })
                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .weight(0.3f)
                                    ) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                        DropDownOutline(
                                            selected = selected,
                                            selectedLabel = selectedLabel
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(0.8f)
                                    ) {
                                        OutlineTextField(
                                            label = stringResource(id = R.string.phone),
                                            placeholder = stringResource(id = R.string.phone),
                                            keyboardType = KeyboardType.Number,
                                            icon = R.drawable.phone,
                                            textValue = phone,
                                            onValueChange = { phone = it },
                                            onClickButton = { phone = "" },
                                            onNext = {
                                                focusManager.moveFocus(
                                                    FocusDirection.Down
                                                )
                                            })
                                    }
                                }
                                EmailOutlineTextField(
                                    label = stringResource(id = R.string.mail),
                                    placeholder = "ejemplo@momo.com",
                                    icon = R.drawable.mail_icon,
                                    keyboardType = KeyboardType.Email,
                                    selected = email,
                                    onNext = {
                                        focusManager.moveFocus(
                                            FocusDirection.Down
                                        )
                                    }
                                )

                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = checkedState.value,
                                    onCheckedChange = { checkedState.value = it }
                                )
                                Text(
                                    stringResource(id = R.string.term_condition),
                                    color = Color.White
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(0.5f)
                                ) {
                                    ButtonBack(onclick = {
                                        navController.navigate(Destination.Client.route)
                                    })
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(0.5f)
                                ) {
                                    ButtonContinue(onclick = {
                                        Log.d("Register.Client", isValidate.toString())
                                        if (isValidate) {
                                            if(checkedState.value) {
                                                clientViewModel.register(
                                                    clientDto = ClientRequest(
                                                        firstName,
                                                        lastName,
                                                        phone,
                                                        selected.value,
                                                        selectedLabel.value,
                                                        email.value
                                                    )
                                                )
                                            }else{
                                                Toast.makeText(context, "Acepta términos y condiciones.", Toast.LENGTH_LONG)
                                                    .show()
                                            }
                                        }
                                    })
                                }
                            }
                        }

                    }

                }
                if(loading){ Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BlueDarkTransparent)
                ){
                    BallClipRotatePulseIndicator()
                }}
            }
        }

    }
}