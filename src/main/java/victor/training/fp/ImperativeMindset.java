package victor.training.fp;

import java.util.List;

public class ImperativeMindset {
   public void useStreams(List<Order> orders) {
   int sum = orders.stream()
           .filter(Order::isActive).mapToInt(Order::getPrice).sum();
      // imperative mindset: let's *add* to ...
      //              coll.add();
      //              change Stuff
      System.out.println("Total price of active orders: " + sum);
   }
}
