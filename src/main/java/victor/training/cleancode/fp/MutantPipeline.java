package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    // NU
    //    var ref = new Object() {int sum = 0;};
    //    final int[] sum = {0};
    //    AtomicInteger sum = new AtomicInteger();

    return orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
  }


  public List<LocalDate> getShipDates(List<Order> orders) {
//        .forEach(order -> order.shipDate().ifPresent(shipDates::add));

    return orders.stream()
        .filter(Order::isActive)
        .map(Order::shipDate)
//        .filter(Optional::isPresent)
//        .map(Optional::get)
        .flatMap(Optional::stream)
        .toList();
  }
}
