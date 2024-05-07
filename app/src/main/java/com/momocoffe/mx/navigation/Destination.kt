package com.momocoffe.mx.navigation

sealed  class Destination(val route: String) {
    object Login: Destination(route = "login")
    object Wellcome: Destination(route = "wellcome")
    object OrderHere: Destination(route = "orderhere")
    object Client: Destination(route = "client")
    object Products: Destination(route = "products")

    companion object {
        fun getStartDestination() = Login.route
    }
}