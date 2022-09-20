package victor.training.fp

import victor.training.fp.model.Order

fun useStreams(orders: List<Order>) {
    var sum = 0
    orders.stream()
        .filter { order: Order -> order.isActive }
        .forEach { order: Order -> sum += order.price }
    println("Total price of active orders: $sum")
}