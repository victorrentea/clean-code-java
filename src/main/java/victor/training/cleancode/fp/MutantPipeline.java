package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    // DON't DO:
    //  var ref = new Object() {
    //      int sum = 0;
    //    };
    //    final int[] sum = {0};
    //    AtomicInteger sum = new AtomicInteger();
    // thread stack
    return orders.parallelStream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
  }


  public List<LocalDate> getShipDates(List<Order> orders) {
    return orders.stream()
        .filter(Order::isActive)
        .flatMap(o->o.shipDate().stream())
        .toList();

//    return orders.stream()
//        .filter(Order::isActive)
//        .map(Order::shipDate)
//        .filter(Optional::isPresent)
//        .map(Optional::get)
//        .toList();
  }
}
