package victor.training.cleancode.exception;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.MemberCard;
import victor.training.cleancode.exception.model.Order;

@RequiredArgsConstructor
public class Biz {
   private final Config config;

   public void applyDiscount(Order order, Customer customer) {
      System.out.println("START");
      if (order.getOfferDate().before(config.getLastPromoDate())) { // TODO inside
//         int points = customer.getMemberCard().orElse(null).getFidelityPoints();
         int points = customer.getMemberCard().map(MemberCard::getFidelityPoints).orElse(0);
         order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
         System.out.println("APPLIED DISCOUNT using " + customer.getMemberCard().map(MemberCard::getBarcode).orElse("N/A"));
      } else {
         System.out.println("NO DISCOUNT");
      }
   }
}

