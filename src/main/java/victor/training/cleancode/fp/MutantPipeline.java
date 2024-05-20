package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
    // evita sa modifici chestii
//        .forEach(order -> {
//          sum += order.price(); // in java nu ai voie dintr-o -> sa modifici o var locala
//        });
    return sum;
  }


  public List<LocalDate> getShipDates(List<Order> orders) {
    return orders.stream()
        .filter(Order::isActive)
//        .filter(order->order.shipDate().isPresent())
//        .map(order -> order.shipDate().get())
        .flatMap(order -> order.shipDate().stream())
        .toList();
//        .forEach(order -> order.shipDate().ifPresent(e -> shipDates.add(e)));
  }
}
