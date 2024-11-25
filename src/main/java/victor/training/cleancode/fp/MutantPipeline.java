package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.List;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    //     var ref = new Object() {int sum = 0;};
    //    final int[] sum = {0};
    //    AtomicInteger sum = new AtomicInteger();
    int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::price)
        .sum();
    return sum;
  }


  public List<LocalDate> getShipDates(List<Order> orders) {
    List<LocalDate> shipDates = orders.stream()
        .filter(Order::isActive)

//        .filter(order -> order.shipDate().isPresent())
//        .map(Order::shipDate)
//        .map(Optional::get)

        .flatMap(order -> order.shipDate().stream())
        .toList(); // = immutable list => for all new code
//        .collect(Collectors.toList()); // =mutalbe list. replace with toList with care!!!!!


//        .forEach(order -> {
//          order.shipDate().ifPresent(date ->
//              shipDates.add(date));
//        });
    return shipDates;
  }
}
