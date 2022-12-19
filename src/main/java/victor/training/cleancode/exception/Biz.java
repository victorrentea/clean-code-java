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
         Integer points = customer.getMemberCard().map(card-> card.getFidelityPoints()).orElse(0); // codu altuia!!! imi crapa mie acum !
         // de ce>  cy ce am gresit in viata?
         // a stiu... am modificat getteru entitatii de domeniu (cele sfinte)
         // tocmai i-am gasit un bug
         order.setPrice(order.getPrice() * (100 - 2 * points) / 100);
      } else {
         System.out.println("NO DISCOUNT");
      }
   }
}

