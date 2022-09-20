package victor.training.fp;

import java.util.List;

public class ImperativeMindset { // stateful

   public void useStreams(List<Order> orders) {
      int sum = orders.stream()
              .filter(Order::isActive)
              .mapToInt(Order::getPrice)
              .sum();
       //              .map(Order::getPrice)
       //              .reduce(0, (a, b) -> a + b)
       // imperative mindset: let's *add* to ...
       System.out.println("Total price of active orders: " + sum);
   }
}
