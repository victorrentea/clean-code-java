package victor.training.fp;

import java.util.List;

public class ImperativeMindset {
   public void useStreams(List<Order> orders) {
      int sum = 0;
      orders.stream()
          .filter(order -> order.isActive())
          .forEach(order -> {
//              imperative mindset: let's *add* to ...
//              sum += order.getPrice();
          });
      System.out.println("Total price of active orders: " + sum);
   }
}
