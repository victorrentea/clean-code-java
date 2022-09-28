package victor.training.cleancode

fun discount(price: Int, quantity: Int): Int {
    var price = price
    if (price > 50) price = price - 2
    if (quantity > 100) price = price - 1
    return price
}