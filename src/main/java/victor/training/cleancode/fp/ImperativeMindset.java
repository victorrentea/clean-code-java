package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImperativeMindset {
  public int totalOrderPrice(List<Order> orders) {
    int sum = 0;
    orders.stream()
        .filter(order -> order.isActive())
        .forEach(order -> {
          // imperative: let's *add* to the sum
          // sum += order.getPrice();
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
