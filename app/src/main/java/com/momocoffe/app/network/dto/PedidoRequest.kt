package com.momocoffe.app.network.dto


data class PedidoRequest(
    val name_client: String,
    val shopping_id: String,
    val kiosko_id: String,
    val columns_pending: Int,
    val product: String
)

data class Producto(
    val id: String,
    val name_product: String,
    val price: Int,
    val image: String,
    val extra: Extra,
    val quanty: Int,
    val subtotal: Int
)

data class Extra(
    val size: Size,
    val milk: Milk,
    val sugar: Sugar,
    val extra_coffee: List<ExtraCoffee>,
    val lid: List<Lid>,
    val sauce: List<Sauce>,
    val temperature: Temperature,
    val color: String,
    val coffee_type: Any
)
data class Size(val name: String, val price: Int)
data class Milk(val name: String, val price: Int)
data class Sugar(val name: String, val price: Int)
data class ExtraCoffee(val name: String, val price: String)
data class Lid(val name: String, val price: Int)
data class Sauce(val name: String, val price: Int)
data class Temperature(val name: String, val price: Int)


fun productoToString(producto: Producto): String {
    return """
        {
            "id": "${producto.id}",
            "name_product": "${producto.name_product}",
            "price": ${producto.price},
            "image": "${producto.image}",
            "extra": {
                "size": { "name": "${producto.extra.size.name}", "price": ${producto.extra.size.price} },
                "milk": { "name": "${producto.extra.milk.name}", "price": ${producto.extra.milk.price} },
                "sugar": { "name": "${producto.extra.sugar.name}", "price": ${producto.extra.sugar.price} },
                "extra_coffee": ${producto.extra.extra_coffee.joinToString(", ", "[", "]") { """{ "name": "${it.name}", "price": "${it.price}" }""" }},
                "lid": ${producto.extra.lid.joinToString(", ", "[", "]") { """{ "name": "${it.name}", "price": ${it.price} }""" }},
                "sauce": ${producto.extra.sauce.joinToString(", ", "[", "]") { """{ "name": "${it.name}", "price": ${it.price} }""" }},
                "temperature": { "name": "${producto.extra.temperature.name}", "price": ${producto.extra.temperature.price} },
                "color": "${producto.extra.color}",
                "coffee_type": "${producto.extra.coffee_type}"
            },
            "quanty": ${producto.quanty},
            "subtotal": ${producto.subtotal}
        }
    """.trimIndent()
}

fun productosToString(productos: List<Producto>): String {
    return productos.joinToString(separator = ", ", prefix = "[", postfix = "]") { productoToString(it) }
}
