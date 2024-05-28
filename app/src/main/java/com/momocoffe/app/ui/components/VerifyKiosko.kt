package com.momocoffe.app.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.network.repository.SessionManager
import com.momocoffe.app.viewmodel.KioskoViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

@Composable
fun VerifyKiosko(navController: NavController,  kioskoViewModel: KioskoViewModel = viewModel()){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
    val preference_kiosko_id = sharedPreferences.getString("kioskoId", null) ?: ""
    val preference_shopping_id = sharedPreferences.getString("shoppingId", null) ?: ""
    val stateExpiredSession  = remember { mutableStateOf(false) }

    val sessionManager = SessionManager()
    sessionManager.stopSession()



    LaunchedEffect(Unit){
        kioskoViewModel.verifyKiosko(kioskoId = preference_kiosko_id)
    }

    LaunchedEffect( kioskoViewModel.kioskoVefiryResultState.value){
        kioskoViewModel.kioskoVefiryResultState.value?.let{ result ->
            when{
                result.isSuccess->{
                    val response = result.getOrThrow()
                    if(!response.state){
                        val editor = sharedPreferences.edit()
                        editor.remove("kioskoId")
                        editor.remove("shoppingId")
                        editor.remove("token")
                        editor.apply()
                    }
                }
                result.isFailure -> {
                    val exception = result.exceptionOrNull()
                    Log.e("Result.KioskoModel", exception.toString())
                }
            }

        }
    }

    LaunchedEffect(key1 = true) {
        SocketHandler.getSocket().on("kiosko-verify-socket", Emitter.Listener {
            val payload: JSONObject = it[0] as JSONObject
            val kioskoId = payload.getString("kiosko_id")
            val shoppingId = payload.getString("shopping_id")
            if (preference_shopping_id == shoppingId) {
                if (preference_kiosko_id == kioskoId) {
                    val editor = sharedPreferences.edit()
                    editor.remove("kioskoId")
                    editor.remove("shoppingId")
                    editor.remove("token")
                    editor.apply()
                    stateExpiredSession.value = true
                }
            }
        })
    }

    if(stateExpiredSession.value) {
        stateExpiredSession.value = false
        navController.navigate(Destination.Login.route)
    }
}