package victor.training.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import victor.training.exception.MyException.ErrorCode;
import victor.training.exception.model.Customer;
import victor.training.exception.model.MemberCard;
import victor.training.exception.model.Order;

import java.io.FileNotFoundException;

//@Service
public class Biz {

   private static final Logger log = LoggerFactory.getLogger(Biz.class);

//   @Transactional
   public void applyDiscount(Order order, Customer customer) {
      //step1 1M INSERT done over 15 mins in 200 separate Tx in different DBs/sytems = BATCH JOB

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

