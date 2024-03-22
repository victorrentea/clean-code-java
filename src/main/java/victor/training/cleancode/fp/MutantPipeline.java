package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    int sum = 0;
    orders.stream()
        .filter(order -> order.isActive())
        .forEach(order -> {
//           sum += order.getPrice(); // let's add to sum
        });
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
