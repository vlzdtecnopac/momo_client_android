package com.momocoffe.app.navigation

sealed  class Destination(val route: String) {
    object Login: Destination(route = "login")
    object Wellcome: Destination(route = "wellcome")
    object OrderHere: Destination(route = "orderhere")
    object Client: Destination(route = "client")
    object ProductsCategoryAndSubProduct: Destination(route = "products/{category}/{subcategory}")
    object ProductsCategory: Destination(route = "products/{category}")
    object Product: Destination(route = "product/{product_id}")
    object Checkout: Destination(route = "checkout")
    object Category: Destination(route = "category")
    object Zettle: Destination(route = "zettle")

    companion object {
        fun getStartDestination(token: String?, kiosko_id: String?): String {
            return if (tokenIsValid(token, kiosko_id)) {
                OrderHere.route
            } else {
                Zettle.route
            }
        }

        private fun tokenIsValid(token: String?, kiosko_id: String?): Boolean {
            return !token.isNullOrEmpty() && !kiosko_id.isNullOrEmpty()
        }
    }
}