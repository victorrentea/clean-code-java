package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MutantPipeline {
  public int totalOrderPrice(List<Order> orders) {
    // DO NOT DO THIS:
    //    var ref = new Object() {int sum = 0;};
    //    AtomicInteger sum = new AtomicInteger();
    //    final int[] sum = {0};

    int sum = orders.parallelStream() // streams are COOL
        .filter(Order::isActive)
        .mapToInt(Order::getPrice)
        .sum();
    // streams are COOL
    // JLS forbids this. Any other language supports it.
    return sum;
  }


  public List<LocalDate> getShipDates(List<Order> orders) {
    List<LocalDate> shipDates = new ArrayList<>();
    orders.stream()
        .filter(Order::isActive)

        .map(Order::shipDate)
        .forEach(o -> o.ifPresent(shipDates::add));

//    shipDates = orders.stream()
//        .filter(Order::isActive)
//        .map(Order::shipDate)
//        .filter(opt->opt.isPresent())
//        .map(Optional::get)
//        .collect(Collectors.toList());
//
//    shipDates = orders.stream()
//        .filter(Order::isActive)
//        .map(Order::shipDate)
//        .flatMap(Optional::stream)
//        .toList();

    return shipDates;
  }
}
