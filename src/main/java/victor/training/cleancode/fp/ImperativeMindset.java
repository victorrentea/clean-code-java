package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImperativeMindset {
   public void mutationInForeach(List<Order> orders) {
     // RAU:
     //  var ref = new Object() {
     //       int sum = 0;
     //     };
     // RAU final int[] sum = {0};
     // RAU AtomicInteger sum = new AtomicInteger();
     int sum = 0; // pe stackul threadului curent
     //      orders.stream()
     //          .filter(order -> order.isActive())
     //          .forEach(order -> {
     //              sum += order.getPrice();// state mutation nu e FP
     //          });

     sum = orders.stream()
             .filter(Order::isActive)
             // mai simplu
             .mapToInt(Order::getPrice)
             .sum();
     // evitati reduce daca poti .sum sau .average
     //              .map(Order::getPrice)
     //              .reduce(0, Integer::sum);
     System.out.println("Total: " + sum);
   }


  public void extractPricesOfActiveOrders(List<Order> orders) {
    List<Integer> prices = new ArrayList<>();
    //      orders.stream()
    //          .filter(Order::isActive)
    //              // .forEach este rau ca da void = te obliga sa SCHIMBI chestii
    //              // daca poti sa-l eviti, evita-l
    //          .forEach(order -> prices.add(order.getPrice()));
    prices = orders.stream()
            .filter(Order::isActive)
            .map(Order::getPrice)
            .collect(Collectors.toList());
    System.out.println("Prices: " + prices);
  }
}
