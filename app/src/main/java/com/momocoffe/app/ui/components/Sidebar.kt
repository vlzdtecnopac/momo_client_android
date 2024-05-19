package com.momocoffe.app.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.momocoffe.app.ui.theme.BlueDarkTransparent
import com.momocoffe.app.ui.theme.redhatFamily
import com.momocoffe.app.ui.theme.stacionFamily
import com.momocoffe.app.viewmodel.ClientViewModel
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.OrangeDark


@Composable
fun Sidebar(clientViewModel: ClientViewModel = viewModel()) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
    val client_id = sharedPreferences.getString("clientId", null) ?: ""
    var email_client by rememberSaveable { mutableStateOf(value = "") }

    var showPopup by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit){
            clientViewModel.getClient("","", client_id)
    }

    LaunchedEffect( clientViewModel.clientResultCheckEmailState.value){
        clientViewModel.clientResultCheckEmailState.value?.let{result ->
            when{
                result.isSuccess -> {
                    val loginResponse = result.getOrThrow()
                    email_client =  if(loginResponse.items.isNotEmpty()){
                      loginResponse.items[0].email
                    }else{
                      "Invitado"
                    }

                }
                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    email_client = ""
                    Log.e("Result.ViewModel", exception.toString())
                }
            }
        }
    }

    Spacer(modifier = Modifier.width(10.dp))
    Box {
        Row(
            modifier = Modifier.clickable {
                      showPopup = true
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                androidx.compose.material.Text(
                    stringResource(id = R.string.wellcome),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = stacionFamily,
                    color = Color.White
                )
                androidx.compose.material.Text(
                    email_client,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = redhatFamily,
                    color = Color.White
                )
            }
            Image(
                painter = painterResource(R.drawable.header_icon_momo),
                contentDescription = stringResource(id = R.string.momo_coffe),
                modifier = Modifier.width(45.dp)
            )

        }

        if (showPopup) {
           PopupBox() {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight()
                        .background(Color.White)
                        .padding(10.dp)
                        .align(Alignment.TopEnd)
                        .zIndex(40F),
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ){
                        Column(
                            modifier = Modifier
                                .weight(0.8f)
                                .padding(vertical = 10.dp),
                            horizontalAlignment = Alignment.End

                        ){
                            IconButton(
                                modifier = Modifier
                                    .width(42.dp)
                                    .height(42.dp)
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(OrangeDark)
                                    .border(
                                        width = 0.6.dp,
                                        color = Color.White,
                                        shape = RoundedCornerShape(50.dp)
                                    )
                                    .padding(10.dp),
                                onClick = {
                                    showPopup = false
                                }
                            ) {
                                Icon(
                                    Icons.Rounded.Close,
                                    contentDescription = stringResource(id = R.string.momo_coffe),
                                    tint = Color.White,
                                    modifier = Modifier.size(34.dp),
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                horizontalArrangement = Arrangement.Center
                            ){
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.5f)
                                        .padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        androidx.compose.material.Text(
                                            stringResource(id = R.string.wellcome),
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight(700),
                                            fontFamily = stacionFamily,
                                            color = BlueDark
                                        )
                                        androidx.compose.material.Text(
                                            email_client,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight(400),
                                            fontFamily = redhatFamily,
                                            color = BlueDark
                                        )
                                    }
                                    Image(
                                        painter = painterResource(R.drawable.header_icon_momo),
                                        contentDescription = stringResource(id = R.string.momo_coffe),
                                        modifier = Modifier.width(45.dp)
                                    )
                                }
                            }
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.End
                        ){
                            Button(
                                onClick = {
                                    val editor = sharedPreferences.edit()
                                    editor.remove("clientId")
                                    editor.apply()
                                    navController.navigate(Destination.OrderHere.route)
                                }
                            ) {
                                Text("Cerrar sesión")
                            }
                        }

                    }


                }
            }
        }
    }
}


@Composable
fun PopupBox( content: @Composable () -> Unit) {
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