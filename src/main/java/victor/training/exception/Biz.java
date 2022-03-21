package victor.training.exception;

import victor.training.exception.model.Customer;
import victor.training.exception.model.MemberCard;
import victor.training.exception.model.Order;

//@Service
public class Biz {

   public void applyDiscount(Order order, Customer customer) {
      System.out.println("START");
      if (order.getOfferDate().before(Config.getLastPromoDate())) { // TODO inside
         System.out.println("APPLYING DISCOUNT");
         Integer points = customer.getMemberCard().map(MemberCard::getFidelityPoints).orElse(0);
         order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
      } else {
         System.out.println("NO DISCOUNT");
      }

   }
}

