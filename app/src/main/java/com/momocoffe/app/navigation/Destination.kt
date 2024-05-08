package com.momocoffe.app.navigation

sealed  class Destination(val route: String) {
    object Login: Destination(route = "login")
    object Wellcome: Destination(route = "wellcome")
    object OrderHere: Destination(route = "orderhere")
    object Client: Destination(route = "client")
    object Products: Destination(route = "products")
    object Checkout: Destination(route = "checkout")

    companion object {
        fun getStartDestination() = Login.route
    }
}