package victor.training.cleancode.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.Order;

import java.io.IOException;
import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class Biz {
   private final Config config;

   public void applyDiscount(Order order, Customer customer) {
      System.out.println("START");
      try {
         if (order.getOfferDate().before(config.getLastPromoDate())) { // TODO inside
            System.out.println("APPLYING DISCOUNT");
            Integer points = customer.getMemberCard().getFidelityPoints();
            order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
         } else {
            System.out.println("NO DISCOUNT");
         }
      } catch (RuntimeException | EndDateNotAvailableException e) {
         throw new RuntimeException("FOr orderID: "+ order.getPrice(), e);
      }
//      } catch (EndDateNotAvailableException e) { // abstraction leak problem> de ce trebuie sa stie biz logic
         // ca getLastPromoDate arunca IOException > asta tradeaza ca lucreaza fisiere.

         // un retry sau un default value intors.
         // TODO hai ca dau un mail
//      }
   }
}
class EndDateNotAvailableException extends Exception{

   public EndDateNotAvailableException(Exception e) {
      super(e);
   }
}

