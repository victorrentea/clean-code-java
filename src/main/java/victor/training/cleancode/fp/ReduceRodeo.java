package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.List;
import java.util.Optional;

public class ReduceRodeo {
  public Order getLastPremium(List<Order> orders) {
    return orders.stream().reduce(null, (prev, order) ->
        order.orderLines().stream().anyMatch(line -> line.product().isPremium()) &&
        (prev == null || order.creationDate().isAfter(prev.creationDate())) ? order : prev
    );
  }
}
