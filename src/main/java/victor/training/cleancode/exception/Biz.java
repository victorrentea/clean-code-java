package victor.training.cleancode.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.Order;

@Service
@RequiredArgsConstructor
public class Biz {
   private final Config config;

   public void applyDiscount(Order order, Customer customer) {
      System.out.println("START");
      if (order.getOfferDate().before(config.getLastPromoDate())) { // TODO inside
         System.out.println("APPLYING DISCOUNT");
         Integer points = customer.getMemberCard()
                 .map(card -> card.getFidelityPoints())
                 .orElse(0);
         order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
      } else {
         System.out.println("NO DISCOUNT");
      }
   }
}

