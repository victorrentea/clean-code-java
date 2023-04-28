package victor.training.cleancode.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Timed;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.MemberCard;
import victor.training.cleancode.exception.model.Order;

import java.io.IOException;
import java.text.ParseException;

import static java.lang.System.currentTimeMillis;

@Service
@RequiredArgsConstructor
public class Biz {
   private final Config config;

   //@Timed // micrometer
//   @Retry
   public void applyDiscount(Order order, Customer customer)  {
      System.out.println("START");
      long t0 = currentTimeMillis();
      try {
         if (order.getOfferDate().before(config.getLastPromoDate())) { // TODO inside
            System.out.println("APPLYING DISCOUNT");
            Integer points = customer.getMemberCard()
                    .map(MemberCard::getFidelityPoints)
                    .orElse(0)                    ;
            order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
         } else {
            System.out.println("NO DISCOUNT");
         }
         //         connection.commit();
//      } catch(SomeException e) {
//         retry()
      } finally {
//         connection.rollback();
         System.out.println("ROLLBACK at the end in any case.");
         long t1 = currentTimeMillis();
         System.out.println("Execution took " + (t1 - t0));
      }
//      } catch (Exception e) { // don't swallow exceptions like this !
////         log.trace()
//         // TODO sent and email to biz in jan 2009 about what to do for this error
//      }
   }
}

