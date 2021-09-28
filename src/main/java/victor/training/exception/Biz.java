package victor.training.exception;

import victor.training.exception.model.Customer;
import victor.training.exception.model.MemberCard;
import victor.training.exception.model.Order;

import java.io.IOException;
import java.text.ParseException;

//@Service
public class Biz {

   public void applyDiscount(Order order, Customer customer) {
      try {
         System.out.println("START");
         if (order.getOfferDate().before(Config.getLastPromoDate())) { // TODO inside
            System.out.println("APPLYING DISCOUNT");
            Integer points = customer.getMemberCard().map(MemberCard::getFidelityPoints).orElse(0);
            order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
         } else {
            System.out.println("NO DISCOUNT");
         }
      } catch (RuntimeException e) {
         throw new RuntimeException("Dragoste pentru sarmanu dev care va debuga erori pe aici" +
                                    "=Crapau pentru orderid="+ order.getId(), e);
      }
   }

   public static void main(String[] args) {
      new Biz().applyDiscount(null, null);
   }
}

