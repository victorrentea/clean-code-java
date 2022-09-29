package victor.training.cleancode

data class ProductLine(val productKey: String, val productCount:Int)
class SplitPhase {
    fun calculateOrderPrice(orderString: String, priceList: Map<String, Int>): Float {
        val line = parse(orderString)
        return computePrice(priceList, line)
    }

    private fun parse(orderString: String): ProductLine {
        val orderData = orderString.split("\\s+".toRegex())
        val productKey = orderData[0].split("-".toRegex())[1]
        val productCount = orderData[1].toInt()
        return ProductLine(productKey, productCount)
    }

    private fun computePrice(
        priceList: Map<String, Int>,
        line: ProductLine
    ): Float {
        val productPrice = priceList[line.productKey]!!
        return (line.productCount * productPrice).toFloat()
    }

}

fun main(args: Array<String>) {
    println(SplitPhase().calculateOrderPrice("Chair-CHR 4", mapOf("CHR" to 5)))
}
