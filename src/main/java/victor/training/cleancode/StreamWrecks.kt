package victor.training.cleancode

import victor.training.cleancode.model.Order
import victor.training.cleancode.model.Product
import victor.training.cleancode.model.ProductRepo
import java.time.LocalDate

data class StreamWrecks(val productApiClient: ProductRepo) {
    fun getFrequentOrderedProducts(orders: List<Order>): List<Product> {
        val recentProductTotals = orders
            .filter { isRecent(it) }
            .flatMap { it.orderLines }
            .groupBy { it.product }
            .mapValues { e -> e.value.sumOf { it.itemCount } }

        val frequentProducts = recentProductTotals
            .filter { (_, value) -> value >= 10 }
            .map { (k, _) -> k }

        val hiddenProductIds = productApiClient.getHiddenProductIds()
        return frequentProducts
            .filter { !it.isDeleted }
            .filter { !hiddenProductIds.contains(it.id) }
    }

    fun isRecent(order: Order) = order.creationDate.isAfter(LocalDate.now().minusYears(1))
}