package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    // compute and return, don;t change
    return orders.parallelStream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();// js: .reduce((acc, order) => acc + order.price, 0);
  }

  public List<LocalDate> getShipDates(List<Order> orders) {
    List<LocalDate> shipDates = orders.stream()
        .filter(Order::isActive)
//        .forEach(order -> {
//          order.shipDate().ifPresent(date -> shipDates.add(date));
//        });
        // merge, nu mutezi nimic dupa ->
//        .filter(order -> order.shipDate().isPresent())
//        .map(order -> order.shipDate().get())
        // mai simplu
        .map(Order::shipDate)
        .flatMap(Optional::stream)
        .toList();
    return shipDates;
  }
}
