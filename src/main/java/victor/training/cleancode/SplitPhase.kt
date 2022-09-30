package victor.training.cleancode

data class ParsedOrder(val productCode:String, val items:Int)
fun calculateOrderPrice(orderString: String, priceList: Map<String, Int>): Float {
    val parsedOrder = parse(orderString)
    return computePrice(priceList, parsedOrder)
}

private fun computePrice(
    priceList: Map<String, Int>,
    parsedOrder: ParsedOrder
): Float {
    val productPrice = priceList[parsedOrder.productCode]!!
    return (parsedOrder.items * productPrice).toFloat()
}

private fun parse(orderString: String): ParsedOrder {
    val orderData = orderString.split("\\s+".toRegex())
    val productCode = orderData[0].split("-".toRegex())[1]
    val productItems = orderData[1].toInt()

    return ParsedOrder(productCode, productItems)
}

fun main() {
    println(calculateOrderPrice("Chair-CHR 4", mapOf("CHR" to 5)))
}