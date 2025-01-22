package victor.training.cleancode.java.fp;

import victor.training.cleancode.java.fp.support.Order;

import java.util.List;

public class ReduceRodeoOpt {
  public Order getLastPremium(List<Order> orders) {
    return orders.stream().reduce(null, (prev, order) ->
        order.shipDate().isPresent() &&
        (prev == null || order.shipDate().get().isAfter(prev.shipDate().get())) ? order : prev
    );
  }
}
