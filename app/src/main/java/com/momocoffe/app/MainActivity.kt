package com.momocoffe.app

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.view.doOnLayout
import com.momocoffe.app.navigation.NavigationScreen
import com.momocoffe.app.ui.client.section.RegisterClient
import com.momocoffe.app.ui.theme.MomoCoffeClientTheme
import com.momocoffe.app.viewmodel.LoginViewModel
import com.momocoffe.app.viewmodel.RegionInternational
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun attachBaseContext(newBase: Context?) {
        val localeToSwitchTo = Locale("es")
        val localeUpdatedContext =
            newBase?.let { RegionInternational.updateLocale(it, localeToSwitchTo) }
        super.attachBaseContext(localeUpdatedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        if (isTablet()) {
            setContent {
                MomoCoffeClientTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        LaunchedEffect(Unit) {
                            // Set up and establish socket connection
                            SocketHandler.setSocket()
                            SocketHandler.establishConnection()
                        }

                        NavigationScreen(viewModel = viewModel)
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

                        LaunchedEffect(Unit) {
                            // Set up and establish socket connection
                            SocketHandler.setSocket()
                            SocketHandler.establishConnection()
                        }

                        NavigationScreen(viewModel = viewModel)
                    }
                }
            }
            //finish()
        }
    }

    private fun isTablet(): Boolean {
        return resources.configuration.smallestScreenWidthDp >= 600 // For tablets with at least 600dp width
    }
}



