package victor.training.cleancode.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.Order;

import java.io.IOException;
import java.text.ParseException;

@RequiredArgsConstructor
@Slf4j
public class Biz {
   private final Config config;

   public void applyDiscount(Order order, Customer customer) {
      System.out.println("START");
     try {
       if (order.getOfferDate().before(config.getLastPromoDate()) &&
           customer.getMemberCard().isPresent()) { // TODO inside
         int points = customer.getMemberCard().get().getFidelityPoints();
         order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
         System.out.println("APPLIED DISCOUNT using " + customer.getMemberCard().get().getBarcode());
       } else {
         System.out.println("NO DISCOUNT");
       }
     } catch (IOException | ParseException e) {
       // TODO io am dat mail
//       e.printStackTrace();// nu apare in log ca iese pe err.
       log.trace("Vai" + e, e);
     }
   }
}

