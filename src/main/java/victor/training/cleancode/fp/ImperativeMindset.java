package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;

public class ImperativeMindset {
   public void mutationInForeach(List<Order> orders) {
      int sum = orders.stream()
              .filter(Order::isActive)
              .mapToInt(Order::getPrice)
              .sum(); // .reduce(0, (a,b)=>a+b);
      System.out.println("Total: " + sum);
   }





   public void accumulateInList(List<Order> orders) {
      List<Integer> prices = orders.stream()
              .filter(Order::isActive)
              //          .forEach(order -> {
              //              prices.add(order.getPrice());
              //          });
              .map(Order::getPrice)
              .toList();
      System.out.println("Prices: " + prices);
   }
}




