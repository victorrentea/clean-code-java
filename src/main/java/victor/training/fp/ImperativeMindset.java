package victor.training.fp;

import org.springframework.stereotype.Service;

import java.util.List;

public class ImperativeMindset {
   public void useStreams(List<Order> orders) {
      int sum = orders.stream()
              .filter(Order::isActive)
              .mapToInt(Order::getPrice)
              .sum();
      // .reduce(0, (a,b) => a+b);

      // imperative mindset: let's *add* to ...
      System.out.println("Total price of active orders: " + sum);
   }

//   public void method() {
//      //coleg
//      System.out.println(sum);
//   }
}
