package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    // replace these
    // var ref = new Object() {
    //      int sum = 0;
    //    };

    //    final int[] sum = {0};

    //    AtomicInteger sum = new AtomicInteger();

    return orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
  }


  public List<LocalDate> getShipDates(List<Order> orders) {
    List<LocalDate> shipDates = new ArrayList<>();
    for (Order order : orders) {
      order.shipDate().ifPresent(date -> shipDates.add(date));
    }
    return shipDates;
  }
}
