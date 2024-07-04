package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MutantPipeline {
  public int totalActiveOrderPrice(List<Order> orders) {
    int sum = 0; // stiva thread curent
    orders.parallelStream()
        .filter(Order::isActive)
//        .forEach(order -> sum += order.price()); // nu poate pt ca ar permite modificari concurente la thread stack
        .forEach(order -> sum += order.price());
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
