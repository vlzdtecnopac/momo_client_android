package com.momocoffe.app.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
import com.momocoffe.app.viewmodel.CartViewModel
import com.momocoffe.app.viewmodel.InvoiceViewModel
import com.momocoffe.app.viewmodel.LoginViewModel

@Composable
fun NavigationScreen(viewModel: LoginViewModel, viewModelCart: CartViewModel, invoiceViewModel: InvoiceViewModel) {

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

        composable(route = Destination.Product.route, arguments = listOf(navArgument("product_id") { defaultValue = "" })) {backStackEntry ->
            val product_id = backStackEntry.arguments?.getString("product_id")

            Product(navController = navController, product_id, viewModelCart)
        }

        composable(route = Destination.ProductsCategoryAndSubProduct.route,
            arguments = listOf(navArgument("category") { defaultValue = "" }, navArgument("subcategory") { defaultValue = "" })
            ) {backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            val subcategory = backStackEntry.arguments?.getString("subcategory")
            Products(navController = navController, category, subcategory, viewModelCart )
        }

        composable(route = Destination.ProductsCategory.route,
            arguments = listOf(navArgument("category") { defaultValue = "" })
        ) {backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            Products(navController = navController, category, viewModelCart = viewModelCart )
        }

        composable(route = Destination.Checkout.route) {
            Checkout(navController = navController, viewModelCart, invoiceViewModel)
        }

        composable(route = Destination.Category.route) {
            Category(navController = navController)
        }

        composable(route = Destination.Zettle.route) {
            ZettlePayment(navController = navController)
        }
    }
}