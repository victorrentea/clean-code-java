package victor.training.cleancode

import java.util.*

class SplitPhase {
    fun calculateOrderPrice(orderString: String, priceList: Map<String?, Int?>): Float {
        val orderData = orderString.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val productPrice = priceList[orderData[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()[1]]
        return (orderData[1].toInt() * productPrice!!).toFloat()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(SplitPhase().calculateOrderPrice("Chair-CHR 4", Collections.singletonMap("CHR", 5)))
        }
    }
}