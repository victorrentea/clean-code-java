package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class ReduceRodeo {
  public Order getLastPremium(List<Order> orders) {
    return orders.stream()
        .filter(Order::hasPremiumProducts)
        .max(comparing(Order::getCreationDate))
        .orElse(null);
  }

}
