package com.momocoffe.app.navigation

sealed  class Destination(val route: String) {
    object Login: Destination(route = "login")
    object Wellcome: Destination(route = "wellcome")
    object OrderHere: Destination(route = "orderhere")
    object Client: Destination(route = "client")
    object Products: Destination(route = "products")
    object Product: Destination(route = "product")
    object Checkout: Destination(route = "checkout")
    object Category: Destination(route = "category")
    object Zettle: Destination(route = "zettle")

    companion object {
        fun getStartDestination(token: String?): String {
            return if (tokenIsValid(token)) {
                OrderHere.route
            } else {
                Zettle.route
            }
        }

        private fun tokenIsValid(token: String?): Boolean {
            return !token.isNullOrEmpty()
        }
    }
}