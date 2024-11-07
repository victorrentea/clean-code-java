package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    //     final int[] sum = {0};
    // var ref = new Object() {
    //      int sum = 0;
    //    };
    //    AtomicInteger sum = new AtomicInteger();
    int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
    // let's add to sum
    return sum;
  }


  public List<LocalDate> getShipDates(List<Order> orders) {
    List<LocalDate> shipDates = orders.stream()
        .filter(Order::isActive)
        .map(Order::shipDate)
//        .filter(Optional::isPresent)
//        .map(Optional::get)
        .flatMap(Optional::stream) // java 11
        .toList(); // 17

//        .collect(Collectors.toList()); // wrong because mutable
//        .collect(Collectors.toUnmodifiableList());

    return shipDates;
  }
}
