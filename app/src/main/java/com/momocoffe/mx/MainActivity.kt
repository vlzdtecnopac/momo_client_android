package com.momocoffe.mx

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ProcessLifecycleOwner
import com.momocoffe.mx.navigation.NavigationScreen
import com.momocoffe.mx.ui.theme.MomoCoffeClientTheme
import com.momocoffe.mx.viewmodel.LoginViewModel
import com.zettle.sdk.ZettleSDK
import com.zettle.sdk.ZettleSDKLifecycle
import com.zettle.sdk.config
import com.zettle.sdk.feature.cardreader.ui.CardReaderFeature
import com.zettle.sdk.feature.manualcardentry.ui.ManualCardEntryFeature
import com.zettle.sdk.feature.qrc.paypal.PayPalQrcFeature
import com.zettle.sdk.feature.qrc.venmo.VenmoQrcFeature

class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private var started: Boolean = false
        private set

    private var isDevMode: Boolean = false
        private set


    fun initZettleSDK(devMode: Boolean) {
        if(started) return
        started = true
        isDevMode = devMode

        val clientId = getString(R.string.client_id)
        val scheme = getString(R.string.redirect_url_scheme)
        val host = getString(R.string.redirect_url_host)
        val redirectUrl = "$scheme://$host"

        val config = config(applicationContext) {
            isDevMode = devMode
            auth {
                this.clientId = clientId
                this.redirectUrl = redirectUrl
            }
            logging {
                allowWhileRoaming = false
            }
            addFeature(CardReaderFeature)
            addFeature(PayPalQrcFeature)
            addFeature(VenmoQrcFeature)
            addFeature(ManualCardEntryFeature)
        }
        ZettleSDK.configure(config)
        //ZettleSDK.start()
        ProcessLifecycleOwner.get().lifecycle.addObserver(ZettleSDKLifecycle())
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

                        NavigationScreen(viewModel = viewModel)
                    }
                }
            }
        } else {
            finish() // Close the app
        }
    }
    private fun isTablet(): Boolean {
        return resources.configuration.smallestScreenWidthDp >= 600 // For tablets with at least 600dp width
    }
}



