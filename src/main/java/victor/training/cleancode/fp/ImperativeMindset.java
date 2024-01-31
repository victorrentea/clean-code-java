package victor.training.cleancode.fp;

import victor.training.cleancode.fp.support.Order;

import java.util.ArrayList;
import java.util.List;

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

    int sum = 0; // this lives on Stack Frame of this thread running the method
    orders.stream() // parallelStream would cause multithreadd access on stack frame
        .filter(Order::isActive)
        .forEach(e -> {
          sum += e.getPrice();
        });
    return sum;
  }


  public List<Integer> getOrderPrices(List<Order> orders) {
    List<Integer> prices = new ArrayList<>();
    orders.stream()
        .filter(order -> order.isActive())
        .forEach(order -> {
          prices.add(order.getPrice());
        });
    return prices;
  }
}
