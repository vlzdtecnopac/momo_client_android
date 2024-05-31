package com.momocoffe.app

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.momocoffe.app.navigation.NavigationScreen
import com.momocoffe.app.network.database.CartDataBase
import com.momocoffe.app.ui.theme.MomoCoffeClientTheme
import com.momocoffe.app.viewmodel.CartViewModel
import com.momocoffe.app.viewmodel.LoginViewModel
import com.momocoffe.app.viewmodel.RegionInternational
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.momocoffe.app.ui.components.AlertInvoiceState
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val viewModelLogin: LoginViewModel by viewModels()
    private var stateInvoice by mutableStateOf("init")

    override fun attachBaseContext(newBase: Context?) {
        val localeToSwitchTo = Locale("es")
        val localeUpdatedContext =
            newBase?.let { RegionInternational.updateLocale(it, localeToSwitchTo) }
        super.attachBaseContext(localeUpdatedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        val intent = intent

        if (intent.hasExtra("zettleStatus")) {
            val zettleStatus: String? = intent.getStringExtra("zettleStatus")
            stateInvoice = when (zettleStatus) {
                "completed" -> "completed"
                "cancelled" -> "cancelled"
                "failed" -> "failed"
                else -> "init"
            }
        } else {
            Log.d("RESULT.ZettlePaymentMomo", "Not output data in ZettlePaymentMomo")
        }

        if (isTablet()) {
            setContent {
                MomoCoffeClientTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val database =
                            Room.databaseBuilder(
                                applicationContext,
                                CartDataBase::class.java,
                                "momo_db"
                            )
                                .build()
                        val dao = database.dao
                        val viewModelDb by viewModels<CartViewModel>(factoryProducer = {
                            object : ViewModelProvider.Factory {
                                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                    return CartViewModel(dao) as T
                                }
                            }
                        })
                        LaunchedEffect(Unit) {
                            // Set up and establish socket connection
                            SocketHandler.setSocket()
                            SocketHandler.establishConnection()
                        }

                        if (stateInvoice.isNotEmpty()) {
                            AlertInvoiceState(stateInvoice, viewCartModel = viewModelDb, ::resetState)
                        }

                        NavigationScreen(viewModel = viewModelLogin, viewModelCart = viewModelDb)
                    }
                }
            }
        } else {
            setContent {
                MomoCoffeClientTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val database =
                            Room.databaseBuilder(
                                applicationContext,
                                CartDataBase::class.java,
                                "momo_db"
                            )
                                .build()
                        val dao = database.dao
                        val viewModelDb by viewModels<CartViewModel>(factoryProducer = {
                            object : ViewModelProvider.Factory {
                                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                    return CartViewModel(dao) as T
                                }
                            }
                        })
                        LaunchedEffect(Unit) {
                            // Set up and establish socket connection
                            SocketHandler.setSocket()
                            SocketHandler.establishConnection()
                        }

                        NavigationScreen(viewModel = viewModelLogin, viewModelCart = viewModelDb)
                    }
                }
            }
        }
    }

    private fun isTablet(): Boolean {
        return resources.configuration.smallestScreenWidthDp >= 600 // For tablets with at least 600dp width
    }

    override fun onBackPressed() {
        // No hacer nada al presionar el botón de "volver atrás"
        // Esto bloquea el comportamiento predeterminado
    }

    private fun resetState() {
        stateInvoice = "init"
    }




}


