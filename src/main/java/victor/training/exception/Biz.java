package victor.training.exception;

import victor.training.exception.model.Customer;
import victor.training.exception.model.MemberCard;
import victor.training.exception.model.Order;

import java.io.IOException;
import java.text.ParseException;

//@Service
public class Biz {
   public static void main(String[] args) {
      new Biz().applyDiscount(null, null);
   }

   public void applyDiscount(Order order, Customer customer)  {
      System.out.println("START");
      try {
         if (order.getOfferDate().before(Config.getLastPromoDate())) {
            //  Checked exceptions sunt "Abstraction Leak": tradeaza implementarea
            System.out.println("APPLYING DISCOUNT");
            Integer points = customer.getMemberCard()
                .map(MemberCard::getFidelityPoints)
                .orElse(0);

            order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
         } else {
            System.out.println("NO DISCOUNT");
         }
         System.out.println("GATA");
      } catch (Exception e) { // cand prinzi pe toate NU CUMVA sa o inghiti > inghiti si NPE
         // TODO ma dcs sa intreb.
      }

   }
}

