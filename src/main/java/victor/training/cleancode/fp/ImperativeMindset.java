package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;

public class ImperativeMindset {
   public void mutationInForeach(List<Order> orders) {
     //     final int[] sum = {0};
     //       AtomicInteger sum = new AtomicInteger();
      int sum = orders.stream()
          .filter(Order::isActive)
          .mapToInt(Order::getPrice)
          .sum();// beton: numeric streams IntStream
//          .map(Order::getPrice).reduce(0, Integer::sum); // merge, dar de evitat
      System.out.println("Total: " + sum);
   }




   public void accumulateInList(List<Order> orders) {
      List<Integer> prices = orders.stream()
          .filter(order -> order.isActive())
          .map(Order::getPrice)
              .toList();
//          .forEach(order -> {
//              prices.add(order.getPrice());
//          });

     // daca totusi side effect
     prices.forEach(this::sideEffect1);
//     prices.forEach(this::sideEffect2); etc
//     prices.forEach(this::sideEffect3);

      System.out.println("Prices: " + prices);
   }

  private void sideEffect1(Integer price) {
    throw new RuntimeException("Method not implemented");
  }
}
