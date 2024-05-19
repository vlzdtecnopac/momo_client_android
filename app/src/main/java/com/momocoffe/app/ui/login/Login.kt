package com.momocoffe.app.ui.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.momocoffe.app.ui.theme.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Text
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.momocoffe.app.ui.login.components.EmailOutTextField
import com.momocoffe.app.ui.login.components.ButtonField
import com.momocoffe.app.ui.login.components.PasswordOutTextField
import com.momocoffe.app.R
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.viewmodel.LoginViewModel
import com.spr.jetpack_loading.components.indicators.BallClipRotatePulseIndicator

@Composable
fun Login(navController: NavHostController, loginViewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val loading = loginViewModel.loadingState.value
    var email by rememberSaveable { mutableStateOf(value = "") }
    var password by rememberSaveable { mutableStateOf(value = "") }
    val isValidate by derivedStateOf { email.isNotBlank() && password.isNotBlank() }

    LaunchedEffect(loginViewModel.loginResultState.value) {
        loginViewModel.loginResultState.value?.let { result ->
            when {
                result.isSuccess -> {
                    val loginResponse = result.getOrThrow()
                    val sharedPreferences =
                        context.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putString("token", loginResponse.token).apply()
                    sharedPreferences.edit().putString("employeeId", loginResponse.employeeID)
                        .apply()
                    sharedPreferences.edit().putString("shoppingId", loginResponse.shoppingID)
                        .apply()
                    navController.navigate(Destination.Wellcome.route)
                }

                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Toast.makeText(context, R.string.error_user_and_password, Toast.LENGTH_LONG)
                        .show()
                    Log.e("Result.ViewModel", exception.toString())
                }
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center
    ){
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
                    painter = painterResource(id = R.drawable.login_img),
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.momo_coffe),
                    modifier = Modifier.width(220.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(id = R.string.start_session_kiosko),
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = redhatFamily,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(id = R.string.enter_gmail_password),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = stacionFamily,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally) // Center the inner box
                        .width(480.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        EmailOutTextField(
                            textValue = email,
                            onValueChange = { email = it },
                            onClickButton = { email = "" },
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        PasswordOutTextField(
                            textValue = password,
                            onValueChange = { password = it },
                            onDone = {
                                focusManager.clearFocus()
                            }
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        ButtonField(
                            text = stringResource(id = R.string.enter),
                            onclick = {
                                if (isValidate) {
                                    loginViewModel.login(email, password)
                                } else {
                                    Toast.makeText(
                                        context,
                                        R.string.error_enter_user_and_password,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            },
                            enabled = true
                        )
                    }
                }

            }
        }
        if (loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueDarkTransparent)
            ) {
                BallClipRotatePulseIndicator()
            }
        }

        if (loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueDarkTransparent)
            ) {
                BallClipRotatePulseIndicator()
            }
        }

    }
}


