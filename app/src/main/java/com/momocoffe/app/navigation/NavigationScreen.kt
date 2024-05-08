package com.momocoffe.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.momocoffe.app.ui.chekout.Checkout
import com.momocoffe.app.ui.client.Client
import com.momocoffe.app.ui.orderhere.OrderHere
import com.momocoffe.app.ui.login.Login
import com.momocoffe.app.ui.products.Products
import com.momocoffe.app.ui.wellcome.WellCome
import com.momocoffe.app.viewmodel.LoginViewModel

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

        composable(route = Destination.Checkout.route) {
            Checkout()
        }
    }
}