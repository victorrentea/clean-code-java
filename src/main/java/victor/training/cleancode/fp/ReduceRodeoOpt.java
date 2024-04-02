package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.List;

public class ReduceRodeoOpt {
  public Order getLastPremium(final List<Order> orders) {
    return orders.stream().reduce(null, (prev, order) ->
        order.shipDate().isPresent() &&
        (prev == null || order.shipDate().get().isAfter(prev.shipDate().get())) ? order : prev
    );
  }
}
