package victor.training.cleancode.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ImperativeMindset {
  int counter;
   public void useStreams(List<Order> orders) {
      List<Integer> prices = new ArrayList<>();
      int sum = 0;
//      orders.stream()
//          .filter(order -> order.isActive())
//          .forEach(order -> {
//              prices.add(order.getPrice());
//          });
      prices = orders.stream()
              .peek(e -> counter++)
              .filter(Order::isActive)
              .map(Order::getPrice).collect(toList());
      System.out.println("Total: " + sum);
      System.out.println("Prices: " + prices);
   }
}
