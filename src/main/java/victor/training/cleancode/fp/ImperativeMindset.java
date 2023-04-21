package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImperativeMindset {
   public void mutationInForeach(List<Order> orders) {
     // var ref = new Object() {
     //       int sum = 0;
     //     };

     // AtomicInteger sum = new AtomicInteger();

     // final int[] sum = {0};


      int sum = orders.stream()
              .filter(Order::isActive)
              .mapToInt(Order::getPrice)
              .sum();
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
      System.out.println("Prices: " + prices);
   }
}
