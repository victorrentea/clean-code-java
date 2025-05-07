package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.List;

import static java.util.Comparator.comparing;

public class E3_ReduceAbuse {
  public Order getLastPremiumOrder(List<Order> orders) {
    return orders.stream()
        .filter(Order::hasPremiumProduct)
        .max(comparing(Order::creationDate))
        .orElse(null);
  }

}
