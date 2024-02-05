package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;

public class ImperativeMindset {
  public int totalOrderPrice(List<Order> orders) {
    // var ref = new Object() {
    //      int sum = 0;
    //    };
    //    final int[] sum = {0};
    //    AtomicInteger sum = new AtomicInteger();

    int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::getPrice)
        .sum();

    // JS style:
//        .reduce(0, (partialSum, order) -> partialSum + order.getPrice(), Integer::sum);
    return sum;
  }


  public List<Integer> getOrderPrices(List<Order> orders) {
    //    orders.stream()
//        .filter(Order::isActive)
//        .forEach(order -> {
//          prices.add(order.getPrice());
//        });
    return orders.stream()
        .filter(Order::isActive)
        .peek(order -> System.out.println("Processing " + order))
        .map(Order::getPrice)
        .toList();
  }
}
