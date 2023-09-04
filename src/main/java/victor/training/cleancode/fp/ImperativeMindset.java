package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImperativeMindset {
  public void activeOrderTotal(List<Order> orders) {
    //   var ref = new Object() {
    //      int sum = 0;
    //    };

    //     final int[] sum = {0};

    //      AtomicInteger sum = new AtomicInteger();

//    int sum = 0;
//    orders.stream()
//        .filter(Order::isActive)
//        .forEach(order -> {
//          // imperative mindset: let's *add* to the sum
//          sum += order.getPrice(); // daca asta ar compila Java ar trebui sa suporte
//          // multi-threaded access la date de pe execution stack.
//        });

//    FP = nu modifici state

    int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::getPrice)
        .sum(); // ❤️

//    int sum = orders.stream()
//        .filter(Order::isActive)
//        .map(Order::getPrice)
//        .reduce(0, (old, p)-> old + p); // JS asa face ca nu are .sum
    System.out.println("Total: " + sum);
  }


  public void accumulateInList(List<Order> orders) {
    List<Integer> prices = orders.stream()
        .filter(order -> order.isActive())
        // .forEach este codesmell daca poti sa-l eviti cu .collect .anyMatch .reduce
          .map(Order::getPrice)
          .collect(Collectors.toList());
//        .forEach(order -> {
//          prices.add(order.getPrice());
//        });
    System.out.println("Prices: " + prices);
  }
}
