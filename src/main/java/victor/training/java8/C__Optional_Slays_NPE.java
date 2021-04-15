package victor.training.java8;

import lombok.Data;

import java.util.UUID;

/* "I call it my billion-dollar mistake.
 * It was the invention of the null reference in 1965..."
 *  -- Sir Charles Antony Richard  */

// Get a discount line to print in UI

class DiscountService {
   public String getDiscountLine(Customer customer) {
      try {
         return "Discount%: " + m4(customer);
      } catch (RuntimeException e) {
         return "";
      }
   }

   private Integer m4(Customer customer) {
      return m3(customer);
   }

   private Integer m3(Customer customer) {
      return m2(customer);
   }

   private Integer m2(Customer customer) {
      return getApplicableDiscountPercentage(customer.getMemberCard());
   }

   private Integer getApplicableDiscountPercentage(MemberCard card) {
      if (card.getFidelityPoints() >= 100) {
         return 5;
      }
      if (card.getFidelityPoints() >= 50) {
         return 3;
      }
      throw new RuntimeException();
   }

   // test: 60, 10, no MemberCard
   public static void main(String[] args) {

   }
}

class MyException extends RuntimeException {
   private final String id = UUID.randomUUID().toString();

   @Override
   public String getMessage() {
      return super.getMessage() + id;
//      return super.getMessage() + System.identityHashCode(this);
   }
}


// VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
class Customer {
   private MemberCard memberCard;

   public Customer() {
   }

   public Customer(MemberCard profile) {
      this.memberCard = profile;
   }

   public MemberCard getMemberCard() {
      return memberCard;
   }
}

@Data
class MemberCard {
   private final int fidelityPoints;
}
