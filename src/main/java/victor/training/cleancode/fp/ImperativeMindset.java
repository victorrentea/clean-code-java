package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;

public class ImperativeMindset {
   public void mutationInForeach(List<Order> orders) {
      int sum = 0;
      orders.stream()
          .filter(order -> order.isActive())
          .forEach(order -> {
             // imperative mindset: let's *add* to the sum
             // sum += order.getPrice();
          });
      System.out.println("Total: " + sum);
   }


   public void accumulateInList(List<Order> orders) {
      List<Integer> prices = new ArrayList<>();
      orders.stream()
          .filter(order -> order.isActive())
          .forEach(order -> {
              prices.add(order.getPrice());
          });
      System.out.println("Prices: " + prices);
   }
}
