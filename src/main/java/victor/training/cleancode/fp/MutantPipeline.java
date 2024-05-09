package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    //     var ref = new Object() {int sum = 0;};
    //    final int[] sum = {0};
    //    AtomicInteger sum = new AtomicInteger();
    int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();// Mantra FP: return, don't change!
    return sum;
  }








  public List<LocalDate> getShipDates(List<Order> orders) {
    var shipDates = orders.stream()
        .filter(Order::isActive)
        .flatMap(order->order.shipDate().stream())
        .toList();

    return shipDates;
  }

//  public List<LocalDate> getShipDates(List<Order> orders) {
//    List<LocalDate> shipDates = new ArrayList<>();
//    orders.stream()
//        .filter(Order::isActive)
//        .forEach(order -> order.shipDate().ifPresent(date -> shipDates.add(date)));
//    return shipDates;
//  }
}
