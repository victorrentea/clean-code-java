package victor.training.cleancode.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.MemberCard;
import victor.training.cleancode.exception.model.Order;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class Biz {
   private final Config config;

   public void applyDiscount(Order order, Customer customer) {
      System.out.println("START");
      if (isBefore(config.getLastPromoDate(), order.getOfferDate())) { // TODO inside
         System.out.println("APPLYING DISCOUNT");
         Integer points = customer.getMemberCard()
             .map(MemberCard::getFidelityPoints)
             .orElse(0);
             //.orElseThrow().getFidelityPoints();
         order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
      } else {
         System.out.println("NO DISCOUNT");
      }
   }

   private boolean isBefore(Date lastPromoDate, Date p) {
      if (p == null) {
         return true;
      }
      return p.before(lastPromoDate);
   }
}

