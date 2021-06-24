package victor.training.java8;

import lombok.Data;

import java.util.Optional;

/* "I call it my billion-dollar mistake.
 * It was the invention of the null reference in 1965..."
 *  -- Sir Charles Antony Richard  */

// Get a discount line to print in UI

class DiscountService {
   // test: 60, 10, no MemberCard
   public static void main(String[] args) {
      System.out.println(new DiscountService().getDiscountLine(new Customer(new MemberCard(60))));
      System.out.println(new DiscountService().getDiscountLine(new Customer(new MemberCard(10))));
      System.out.println(new DiscountService().getDiscountLine(new Customer()));
   }

   public String getDiscountLine(Customer customer) {
		return customer.getMemberCard()
			.flatMap(this::getApplicableDiscountPercentage) // asta face Optional sa devina MONADA
			.map(integer -> "Discount%: " + integer)
			.orElse("");
   }

   private Optional<Integer> getApplicableDiscountPercentage(MemberCard card) {
      if (card.getFidelityPoints() >= 100) {
         return Optional.of(5);
      }
      if (card.getFidelityPoints() >= 50) {
         return Optional.of(3);
      }
      return Optional.empty();
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

   public Optional<MemberCard> getMemberCard() {
		return Optional.ofNullable(memberCard);
   }
}

@Data
class MemberCard {
   private final int fidelityPoints;
}
