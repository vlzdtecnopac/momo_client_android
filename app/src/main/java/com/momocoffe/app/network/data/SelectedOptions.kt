package com.momocoffe.app.network.data

data class SelectedOptions(
    var temperatureFood: Int = 0,
    var size: Int = 0,
    var milk: Int = 0,
    var sugar: Int = 0,
    var extraShot: Int = 0,
    var tapLib: Int = 0,
    var temperatureDrink: Int = 0,
    var sauce: Int = 0
){
    fun calculatePrice(): Int {
        return listOf(temperatureFood, size, milk, sugar, extraShot, tapLib, temperatureDrink, sauce).sum()
    }
}