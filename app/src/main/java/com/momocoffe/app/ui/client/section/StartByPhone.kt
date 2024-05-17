package com.momocoffe.app.ui.client.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.client.components.ButtonBack
import com.momocoffe.app.ui.client.components.DropDownOutline
import com.momocoffe.app.ui.client.components.OutTextField
import com.momocoffe.app.ui.login.components.ButtonField
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.ui.theme.stacionFamily


@Composable
fun StartByPhone(navController: NavController) {
    var phone by remember { mutableStateOf(value = "") }
    val selected = remember { mutableStateOf(value = "") }
    val selectedLabel = remember { mutableStateOf(value = "") }
    val focusManager = LocalFocusManager.current
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
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    Box {}
                    Column(
                        modifier = Modifier
                            .widthIn(0.dp, 480.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                                OutTextField(
                                    textValue = phone,
                                    onValueChange = { phone = it },
                                    onClickButton = { phone = "" },
                                    text = stringResource(id = R.string.phone),
                                    keyboardType = KeyboardType.Phone,
                                    icon = painterResource(R.drawable.phone),
                                    onNext = {
                                        focusManager.moveFocus(
                                            FocusDirection.Down
                                        )
                                    }
                                )
                            }

                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(30.dp))
                            ButtonField(
                                text = stringResource(id = R.string.enter),
                                onclick = { /*TODO*/ },
                                enabled = true
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.padding(end = 10.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        ButtonBack(onclick = {
                            navController.popBackStack()
                        })
                    }
                }

            }
        }
    }
}