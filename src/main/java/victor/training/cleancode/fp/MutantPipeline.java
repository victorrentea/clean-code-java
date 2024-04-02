package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MutantPipeline {
  public int totalActiveOrderPrice(final List<Order> orders) {
    // NU cu:
    //    var ref = new Object() {int sum = 0;};
    //    final int[] sum = {0};
    //    AtomicInteger sum = new AtomicInteger();
    final int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
    return sum;
  }


  public List<LocalDate> getShipDates(final List<Order> orders) {
    return orders.stream()
        .filter(Order::isActive)
        .map(Order::shipDate)
        .flatMap(Optional::stream)
        .toList();
  }
}
