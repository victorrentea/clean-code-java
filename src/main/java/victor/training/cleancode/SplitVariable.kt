package victor.training.cleancode

import java.lang.IllegalArgumentException

object SplitVariable {
    // @see test
    @JvmStatic
    fun discount(price: Int, quantity: Int): Int {
        var price = price
        if (price > 50) price = price - 2
        if (quantity > 100) price = price - 1
        return price
    }
}


fun A(): Int {
    throw IllegalArgumentException()
    return 1
}
fun B(): Result<Int> {
    return Result.failure(IllegalArgumentException())
    return Result.success(1)
}
//
//    return CamResult.failure(IllegalArgumentException())
//    return CamResult.failure(errorCode)
//    return CamResult.failure("message")

