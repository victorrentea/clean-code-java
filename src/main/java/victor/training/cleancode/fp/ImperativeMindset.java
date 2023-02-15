package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImperativeMindset {
   public void mutationInForeach(List<Order> orders) {
     int sum = orders.stream()
             .filter(Order::isActive)
             .mapToInt(Order::getPrice)
             .sum();
     System.out.println("Total: " + sum);
   }


   public void accumulateInList(List<Order> orders) {
     //      List<Integer> prices = new ArrayList<>();
     //      orders.stream()
     //          .filter(order -> order.isActive())
     //          .forEach(order -> {
     //              prices.add(order.getPrice());
     //          });
     List<Integer> prices = orders.stream().filter(Order::isActive)
             .map(Order::getPrice)
             .toList();
     System.out.println("Prices: " + prices);
   }
}
