package victor.training.cleancode.java.fp;

import victor.training.cleancode.java.fp.support.Order;

import java.util.List;

public class ReduceRodeo {
  public Order getLastPremium(List<Order> orders) {
    return orders.stream().reduce(null, (prev, order) ->
        order.orderLines().stream().anyMatch(line -> line.product().isPremium()) &&
        (prev == null || order.creationDate().isAfter(prev.creationDate())) ? order : prev
    );
  }
}
