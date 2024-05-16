package com.momocoffe.app.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.momocoffe.app.App
import com.momocoffe.app.ui.category.Category
import com.momocoffe.app.ui.chekout.Checkout
import com.momocoffe.app.ui.client.Client
import com.momocoffe.app.ui.orderhere.OrderHere
import com.momocoffe.app.ui.login.Login
import com.momocoffe.app.ui.products.Products
import com.momocoffe.app.ui.products.section.Product
import com.momocoffe.app.ui.wellcome.WellCome
import com.momocoffe.app.ui.zettle.ZettlePayment
import com.momocoffe.app.viewmodel.LoginViewModel

@Composable
fun NavigationScreen(viewModel: LoginViewModel) {

    val navController = rememberNavController()
    val sharedPreferences = App.instance.getSharedPreferences("momo_prefs", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("token", null) ?: ""
    val kiosko_id = sharedPreferences.getString("kioskoId", null) ?: ""

    NavHost(
        navController = navController,
        startDestination = Destination.getStartDestination(token, kiosko_id)
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

        composable(route = Destination.Product.route) {
            Product(navController = navController)
        }

        composable(route = Destination.Products.route) {
            Products(navController = navController)
        }

        composable(route = Destination.Checkout.route) {
            Checkout(navController = navController)
        }

        composable(route = Destination.Category.route) {
            Category(navController = navController)
        }

        composable(route = Destination.Zettle.route) {
            ZettlePayment(navController = navController)
        }
    }
}