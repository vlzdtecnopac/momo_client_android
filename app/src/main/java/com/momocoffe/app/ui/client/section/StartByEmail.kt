package com.momocoffe.app.ui.client.section

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.ui.theme.stacionFamily
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.client.components.*
import com.momocoffe.app.ui.login.components.ButtonField
import com.momocoffe.app.viewmodel.ClientViewModel

@Composable
fun StartByEmail(navController: NavController, clientViewModel: ClientViewModel = viewModel()) {
    val context = LocalContext.current
    var email by remember { mutableStateOf(value = "") }
    val focusManager = LocalFocusManager.current
    val isValidate by derivedStateOf { email.isNotBlank() }


    LaunchedEffect(clientViewModel.clientResultSession.value){
        clientViewModel.clientResultSession.value?.let{ result ->
            when {
                result.isSuccess -> {
                    navController.navigate(Destination.Category.route)
                }
                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Toast.makeText(context, R.string.start_failed_session, Toast.LENGTH_LONG)
                        .show()
                    Log.d("Result.ClientViewModel", exception.toString())
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(4f),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.client_session),
                        contentDescription = stringResource(id = R.string.momo_coffe),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(7f)
                        .background(BlueDark),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,

                    ) {

                        Box{}
                        Column(
                            modifier =Modifier
                                .widthIn(0.dp, 900.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = stringResource(id = R.string.momo_coffe),
                                modifier = Modifier.width(190.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                stringResource(id = R.string.start_session),
                                color = Color.White,
                                fontSize = 30.sp,
                                fontFamily = redhatFamily,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                stringResource(id = R.string.enter_yuor_email),
                                color = Color.White,
                                fontSize = 20.sp,
                                fontFamily = stacionFamily,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Box(
                                modifier =Modifier.width(400.dp)
                            ) {
                                OutTextField(
                                    textValue = email,
                                    onValueChange = { email = it },
                                    onClickButton = { email = "" },
                                    text = stringResource(id = R.string.mail),
                                    keyboardType = KeyboardType.Email,
                                    icon = painterResource(R.drawable.mail_icon),
                                    onNext = {
                                        focusManager.moveFocus(
                                            FocusDirection.Down
                                        )
                                    }
                                )
                            }

                            Spacer(modifier = Modifier.height(30.dp))
                            ButtonField(
                                text = stringResource(id = R.string.enter),
                                onclick = {
                                    if(isValidate) {
                                        clientViewModel.getSessionEmailClient(email)
                                    }else{
                                        Toast.makeText(
                                            context,
                                            R.string.required_email,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                },
                                enabled = true
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.End
                        ) {
                            Spacer(modifier = Modifier.height(30.dp))
                            ButtonBack(onclick = {
                                navController.popBackStack()
                            })
                        }

                }
            }
        }
    }
}



