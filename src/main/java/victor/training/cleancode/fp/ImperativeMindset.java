package victor.training.cleancode.fp;

import java.util.ArrayList;
import java.util.List;

public class ImperativeMindset {
   public void useStreams(List<Order> orders) {
      List<Integer> prices = new ArrayList<>();
      int sum = 0;
      orders.stream()
          .filter(order -> order.isActive())
          .forEach(order -> {
             // imperative mindset: let's *add* to ...
             // sum += order.getPrice();
             // prices.add(order.getPrice());
          });
      System.out.println("Total: " + sum);
      System.out.println("Prices: " + prices);
   }
}
