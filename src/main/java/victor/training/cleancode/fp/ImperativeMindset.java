package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

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


  public List<Integer> getOrderPrices(List<Order> orders) {
    List<Integer> prices = new ArrayList<>();
    orders.stream()
        .filter(order -> order.isActive())
        .forEach(order -> {
          prices.add(order.getPrice());
        });
    return prices;
  }
}
