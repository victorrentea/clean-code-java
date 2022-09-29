package victor.training.cleancode

fun discount(price: Int, quantity: Int): Int {
    val priceDiscount = if (price > 50) 2 else 0
    val volumeDiscount = if (quantity > 100) 1 else 0
    return price - priceDiscount - volumeDiscount
}