package victor.training.cleancode.exception;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.Order;

import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
public class Biz {
   private static final Logger log = LoggerFactory.getLogger(Biz.class);
   private final Config config;

   @SneakyThrows
   public void applyDiscount(Order order, Customer customer) {
      System.out.println("START");
//      try {
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
//      } catch (Exception e) {
//         // shaworma, ca inghiti orice cu de toate.
//      }
   }
}

