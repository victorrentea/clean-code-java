package victor.training.cleancode

import org.junit.jupiter.api.Test

//@JvmInline
//value class CId(val id: Long) {}

class MicroTypes {

    //<editor-fold desc="Unknown source of data">
    fun extremeFP(): Map<CustomerId, List<ProductToCount>> {
        val customerId = 1L
        val product1Count = 2
        val product2Count = 4
        return mapOf(
            CustomerId(customerId) to listOf(
                ProductToCount("Table", product1Count),
                ProductToCount("Chair",  product2Count))
        )
    }

    //</editor-fold>
    data class CustomerId(val id:Long)
    data class ProductToCount(val productName:String, val count:Int)

    @Test
    fun lackOfAbstractions() {
        val map = extremeFP()
        for (cid in map.keys) {
            val productLine = map[cid]!!
                .joinToString(", ") { "${it.productName} of ${it.count}" }
            println("cid=$cid got $productLine")
        }
    }
}


//@JvmInline
//value class PowerDbw(private val power: Double) {
//    infix fun addLinear(other: PowerDbw) = other.power + power
//}
//val Double.dBW get() = PowerDbw(this)
//val powerA = 10.0.dBW
//val powerB = 15.0.dBW
//val totalPower = powerA addLinear powerB // Much better!
//
//
//fun lessMem(p1:PowerDbw, p2: PowerDbw) {
//    println(p1)
//}