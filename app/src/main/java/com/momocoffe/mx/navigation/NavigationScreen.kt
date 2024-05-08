package com.momocoffe.mx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.momocoffe.mx.ui.client.Client
import com.momocoffe.mx.ui.orderhere.OrderHere
import com.momocoffe.mx.ui.login.Login
import com.momocoffe.mx.ui.products.Products
import com.momocoffe.mx.ui.wellcome.WellCome
import com.momocoffe.mx.viewmodel.LoginViewModel

@Composable
fun NavigationScreen(viewModel: LoginViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.getStartDestination()
    ) {
        composable(route = Destination.Login.route) {
            Login(navController = navController)
        }

        composable(route = Destination.Wellcome.route) {
            WellCome(navController = navController)
        }

        composable(route = Destination.OrderHere.route) {
            OrderHere(navController = navController)
        }

        composable(route = Destination.Client.route) {
            Client(navController = navController)
        }

        composable(route = Destination.Products.route) {
            Products(navController = navController)
        }
    }
}