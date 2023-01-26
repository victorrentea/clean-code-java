package victor.training.cleancode.fp;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ImperativeMindset {
  public void useStreams(List<Order> orders) {
    int sum = orders.stream()
            .filter(Order::isActive)
            .mapToInt(Order::getPrice)
            .sum();

    //      orders.stream()
    //          .filter(order -> order.isActive())
    //          .forEach(order -> {
    //              prices.add(order.getPrice()); // am modificat !!! lista
    //          });
     // DA, FP corect
    List<Integer> prices = orders.stream()
            .filter(order -> order.isActive())
            .map(Order::getPrice)
            .collect(toList());


    System.out.println("Total: " + sum);
    System.out.println("Prices: " + prices);
  }
}
