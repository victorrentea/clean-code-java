package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    // sa nu te prind cu
    //    var ref = new Object() {int sum = 0;};
    //    final int[] sum = {0};
    //    AtomicInteger sum = new AtomicInteger();

    int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
    //        .forEach(order -> sum += order.price());// forEach e naspa

    return sum;
  }


  public List<LocalDate> getShipDates(List<Order> orders) {
    List<LocalDate> shipDates = new ArrayList<>();
    orders.stream()
        .filter(order -> order.isActive())
        .forEach(order -> {
          order.shipDate().ifPresent(date -> shipDates.add(date));
        });
    return shipDates;
  }
}
