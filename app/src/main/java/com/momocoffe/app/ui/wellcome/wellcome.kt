package com.momocoffe.app.ui.wellcome


import android.content.Context
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.foundation.*
import androidx.compose.ui.unit.*
import com.momocoffe.app.ui.theme.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.spr.jetpack_loading.components.indicators.BallBeatIndicator
import com.momocoffe.app.ui.wellcome.component.CardOption
import com.momocoffe.app.R
import com.momocoffe.app.network.response.DataKiosko
import com.momocoffe.app.network.response.ItemEmployee
import com.momocoffe.app.viewmodel.KioskoModel
import com.momocoffe.app.viewmodel.ShoppingViewModel
import com.momocoffe.app.viewmodel.WelcomeViewModel


import kotlinx.coroutines.delay


@Composable
fun WellCome(navController: NavController,
             welcomeViewModel: WelcomeViewModel = viewModel(),
             shoppingViewModel: ShoppingViewModel = viewModel(),
             kioskoModel: KioskoModel = viewModel()
             ) {
    val context = LocalContext.current
    var shoppingID by remember { mutableStateOf(value = "") }
    var shoppingData: ItemEmployee? by remember { mutableStateOf(null) }
    var kioskoData:  DataKiosko? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        val sharedPreferences = context.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
        val employeeID = sharedPreferences.getString("employeeId", "") ?: ""
        welcomeViewModel.getEmployee(employeeID)
        delay(2000)
        welcomeViewModel.employeeResultState.value?.let { result ->
            when {
                result.isSuccess -> {
                    val employeeResponse = result.getOrThrow()
                    Log.d("Result.WelcomeModel", employeeResponse.items[0].shoppingID)
                    shoppingID = employeeResponse.items[0].shoppingID
                    shoppingData = employeeResponse.items[0]
                }
                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Log.d("Result.WelcomeModel", exception.toString())
                }

                else -> {
                    Log.d("Result.WelcomeModel", "Error")
                }
            }
        }
        shoppingViewModel.getShopping(shoppingID)

    }

    LaunchedEffect(shoppingViewModel.shoppingResultState.value) {
        shoppingViewModel.shoppingResultState.value?.let {result ->
            when {
                result.isSuccess -> {
                    val shoppingResponse = result.getOrThrow()
                    kioskoModel.activeKiosko(shoppingID =  shoppingResponse.items[0].shoppingID)
                }
                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Log.d("Result.WelcomeModel", exception.toString())
                }

                else -> {
                    Log.d("Result.WelcomeModel", "Error")
                }
            }
        }
    }

    LaunchedEffect(kioskoModel.kioskoResultState.value) {
        kioskoModel.kioskoResultState.value?.let { result ->
            when {
                result.isSuccess -> {
                    val sharedPreferences = context.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
                    val kioskoResponse = result.getOrThrow()
                    kioskoData = kioskoResponse.data
                    sharedPreferences.edit().putString("shoppingId", kioskoResponse.data.kioskoID).apply()
                    if(shoppingData != null && kioskoData != null){
                        navController.navigate("orderhere")
                    }
                }
                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Log.d("Result.WelcomeModel", exception.toString())
                }

                else -> {
                    Log.d("Result.WelcomeModel", "Error")
                }
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(BlueDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.momo_coffe),
            modifier = Modifier.width(190.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.welcome),
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = redhatFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            stringResource(id = R.string.pair_device_kiosko),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = stacionFamily,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.widthIn(0.dp, 850.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val textModifier = Modifier
                .padding(5.dp)
                .weight(0.5f)
            Column(modifier = textModifier,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
                shoppingData.let { data ->
                    if (data != null) {
                        CardOption(
                            text = data.nameShopping,
                            icon = painterResource(R.drawable.kiosko),
                            color = Color.White,
                            textColor = BlueDark
                        )
                    }
                }

            }

            Column(
                modifier = Modifier
                    .height(200.dp)
                    .weight(0.3f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BallBeatIndicator()
            }

            Column(modifier = textModifier,
                horizontalAlignment = Alignment.CenterHorizontally) {
                kioskoData.let {data ->
                    if (data != null) {
                        CardOption(
                            text = data.nombre,
                            icon = painterResource(R.drawable.kds_off),
                            color = BlueDark,
                            textColor = Color.White
                        )
                    }
                }

            }
        }
    }
}
