package victor.training.fp;

import java.util.List;

public class ImperativeMindset {
   public void useStreams(List<Order> orders) {
      int sum = orders.stream()
          .filter(Order::isActive)
          .mapToInt(Order::getPrice)
          .sum();
      System.out.println(sum);
   }
}
