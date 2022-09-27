package victor.training.cleancode

import org.junit.jupiter.api.Test

class MicroTypes {
    //<editor-fold desc="Unknown source of data">
    fun extremeFP(): Map<CustomerId, List<ProductCounts>> {
        val customerId = CustomerId(1)
        val product1Count = 2
        val product2Count = 4
        return mapOf(
            customerId to listOf(
               ProductCounts("Table",  product1Count),
               ProductCounts("Chair",  product2Count)
            )
        )
    }
    //</editor-fold>

    data class ProductCounts(val productName:String, val count: Int)


    @JvmInline
    value class CustomerId(val id:Int)

    // + more memory
//    typealias CustomerId = Int

    @Test
    fun lackOfAbstractions() {
        val map= extremeFP()
        for (cid in map.keys) {
            val pl = formatCustomer(map, cid)
            println("cid=${cid} got $pl")
        }
    }

    private fun formatCustomer(
        map: Map<CustomerId, List<ProductCounts>>,
        cid: CustomerId
    ) = map[cid]!!
        .joinToString(", ")
        { "${it.count} of ${it.productName}" }
}
//typealias CustomerId = Int