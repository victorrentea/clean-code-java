package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.List;
import java.util.function.Predicate;

public class ImperativeMindset {
   public void mutationInForeach(List<Order> orders) {
      int sum = orders.stream()
              .filter(Order::isActive)
              .mapToInt(Order::getPrice)
              .sum(); // THE corect FP way is NOT TO CHANGE, but to return

      // imperative mindset: let's *add* to the sum
      System.out.println("Total: " + sum);
   }


   public void accumulateInList(List<Order> orders) {
      List<Integer> prices = orders.stream()
              .filter(order -> order.isActive())
              .map(Order::getPrice)
              .toList();
      System.out.println("Prices: " + prices);
   }
}
