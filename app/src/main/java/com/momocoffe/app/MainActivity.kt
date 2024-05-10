package com.momocoffe.app

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ProcessLifecycleOwner
import com.momocoffe.app.navigation.NavigationScreen
import com.momocoffe.app.ui.theme.MomoCoffeClientTheme
import com.momocoffe.app.viewmodel.LoginViewModel
import com.zettle.sdk.ZettleSDK
import com.zettle.sdk.ZettleSDKLifecycle
import com.zettle.sdk.config
import com.zettle.sdk.feature.cardreader.ui.CardReaderFeature
import com.zettle.sdk.feature.manualcardentry.ui.ManualCardEntryFeature
import com.zettle.sdk.feature.qrc.paypal.PayPalQrcFeature
import com.zettle.sdk.feature.qrc.venmo.VenmoQrcFeature

class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    fun initZettleSDK() {
        val clientId = getString(R.string.client_id)
        val scheme = getString(R.string.redirect_url_scheme)
        val host = getString(R.string.redirect_url_host)
        val redirectUrl = "$scheme://$host"

        val config = config(applicationContext) {
            isDevMode = false
            auth {
                this.clientId = clientId
                this.redirectUrl = redirectUrl
            }
            addFeature(CardReaderFeature.Configuration)
            addFeature(PayPalQrcFeature.Configuration)
            addFeature(VenmoQrcFeature.Configuration)
            addFeature(ManualCardEntryFeature.Configuration)
        }
        val sdk = ZettleSDK.configure(config)

        // Attach the SDKs lifecycle observer to your lifecycle. It allows the SDK to
        // manage bluetooth connection in a more graceful way
        ProcessLifecycleOwner.get().lifecycle.addObserver(ZettleSDKLifecycle())

        // Alternatively, start the SDK manually, but remember to also stop it manually through sdk.stop() or ZettleSDK.instance?.stop()
        sdk.start()
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
                            // Initialize Zettle SDK
                            initZettleSDK()
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
                            // Initialize Zettle SDK
                            initZettleSDK()
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



