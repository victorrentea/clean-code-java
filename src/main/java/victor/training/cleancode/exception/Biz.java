package victor.training.cleancode.exception;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.Order;

@RequiredArgsConstructor
public class Biz {
   private final Config config;

   public void applyDiscount(Order order, Customer customer) {
      System.out.println("START");
      if (order.getOfferDate().before(config.getLastPromoDate())) { // TODO inside
         int points = customer.getMemberCard().getFidelityPoints();
         order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
         System.out.println("APPLIED DISCOUNT using " + customer.getMemberCard().getBarcode());
      } else {
         System.out.println("NO DISCOUNT");
      }
   }
}

