package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ImperativeMindset {
  // jr care a auzit ca .stream e cool 😎
  public void computeTotalOrderPrice(List<Order> orders) {
    // stream.forEach si optional.ifPresent sunt code smell daca doar produc DATE
    // EVITA:
    // -    var ref = new Object() {int sum = 0;};
    // -    final int[] sum = {0};
    // -    AtomicInteger sum = new AtomicInteger();

    int sum = orders.stream()
        .filter(Order::isActive)
        .mapToInt(Order::getPrice)
        .reduce(0, (a,b)->a+b); // naspa dpdv Clean Code
//        .sum();// mai expresiv❤️
    //           sum += order.getPrice(); // nu compileaza pt ca sum e pe STACK si nu poate fi modificat din lambda

    //orders.forEach(o -> repo.save(o)); // side effects, e ok .forEach(sideEffects)
    //orders.forEach(o -> emailSender.send(email)); // side effects, e ok .forEach(sideEffects)

    System.out.println("Total: " + sum);
  }
  OrderRepo repo;
// interesante daca colectia e MARE si e tinuta in MEMORIA TA
// stream().forEach( -> ) in ordine
// stream().reduce( -> )
// parallelStream().forEach( -> atomicInteger.increment(e) ) alandala pe eg 8 threaduri
// parallelStream().sum() colectorul de sub operatorul sum este multi-threadable


  public void findPricesOfActiveOrders(List<Order> orders) {
    List<Integer> prices = orders.stream()
        .filter(Order::isActive)
        .map(Order::getPrice)
        .toList(); // imutable list

    //    orders.stream()
//        .filter(order -> order.isActive())
//        .forEach(order -> {
//          prices.add(order.getPrice()); // MUTATION PE COLECTIE : INTERZIS
//        });
    System.out.println("Prices: " + prices);

  }
//    Optional<Order> orderOpt;
////    orderOpt.ifPresent(o -> orders.add(o));
//    orderOpt.stream()
}
