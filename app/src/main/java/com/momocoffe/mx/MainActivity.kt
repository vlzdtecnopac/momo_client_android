package com.momocoffe.mx

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.momocoffe.mx.navigation.NavigationScreen
import com.momocoffe.mx.ui.theme.MomoCoffeClientTheme
import com.momocoffe.mx.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
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



