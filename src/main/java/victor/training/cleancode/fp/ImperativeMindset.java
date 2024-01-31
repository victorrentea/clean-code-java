package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImperativeMindset {
//  int sumField = 0;// on the heap.
  public int totalOrderPrice(List<Order> orders) {
    // NEVER DO THIS:
//    var ref = new Object() { // WTF is this !?!?!
//      // anonymous class scoped to SINGLE METHOD!!
//      int sum = 0;
//    };
//    final int[] sum = {0};
//    AtomicInteger sum = new AtomicInteger();
    int sum = orders.stream() // parallelStream would cause multithreadd access on stack frame
        .filter(Order::isActive)
        .mapToInt(Order::getPrice)
        .sum(); // this lives on Stack Frame of this thread running the method
    // parallelStream would cause multithreadd access on stack frame
    return sum;
  }


  public List<Integer> getOrderPrices(List<Order> orders) {
    List<Integer> prices = orders.stream()
        .filter(order -> order.isActive())
//        .forEach(order -> { // for each used to accumulate data is a code smell.
//          // worse than a good ol' for()
//          prices.add(order.getPrice());
//        });
        .map(Order::getPrice)
        .toList();
    return prices;
  }
}
