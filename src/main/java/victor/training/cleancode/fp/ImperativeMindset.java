package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;

public class ImperativeMindset {
  public int totalOrderPrice(List<Order> orders) {
    int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::getPrice)
        .sum();
    // imperative: let's *add* to the sum
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
