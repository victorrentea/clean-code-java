package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.List;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    //    var ref = new Object() {int sum = 0;};
    //    final int[] sum = {0};
    //    var ref = new Object() {int sum = 0;};
    return orders.parallelStream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum(); // compute and return instead of changing
  }


  public List<LocalDate> getShipDates(List<Order> orders) {
    List<LocalDate> shipDates = orders.stream()
        .filter(Order::isActive)
        //v1 imperative mindset, avoid
//        .forEach(order -> {
//          order.shipDate().ifPresent(date -> shipDates.add(date));
//        });

        //v2
//        .map(Order::shipDate)
//        .filter(Optional::isPresent)
//        .map(Optional::get)

        //v3
        .flatMap(order -> order.shipDate().stream())
        .toList();
    return shipDates;
  }
}
