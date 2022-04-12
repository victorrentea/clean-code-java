
package victor.training.fp;

import lombok.Data;
import victor.training.exception.model.Customer;
import victor.training.exception.model.MemberCard;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("ConstantConditions")
public class Optionals {
   public static void main(String[] args) {
      // test: 60, 10, no MemberCard
      System.out.println(getDiscountLine(new Customer(new MemberCard(60))));
      System.out.println(getDiscountLine(new Customer(new MemberCard(10))));
      System.out.println(getDiscountLine(new Customer()));
   }

   public static String getDiscountLine(Customer customer) {
//      Optional.ofNullable(customer).ifPresent(customer1 -> System.out.println(customer1));
      if (customer!=null) System.out.println(customer);

      return customer.getMemberCard()
          .flatMap(Optionals::getApplicableDiscountPercentage)
          .map(discount -> "Discount: " + discount.getGlobalPercentage())
          .orElse("");
   }

   // in Clean Code nu trebuie sa accepti paramtetrii care pot fi NULL > asta te obliga in fct ta sa te aperi de dusmanu
   // in Clean Code nici nu INTORCI null la caller, ci Optional<> sau arunci exceptie cand nu are sens sa intorci null.
   private static Optional<Discount> getApplicableDiscountPercentage(MemberCard card) {
//		if (card == null) {
//			return Optional.empty();
//		}
      if (card.getFidelityPoints() >= 100) {
         return Optional.of(new Discount(5));
      }
      if (card.getFidelityPoints() >= 50) {
         return Optional.of(new Discount(3));
      }
      return Optional.empty();
   }
}

@Data
class Discount {
   private final int globalPercentage;
   private Map<String, Integer> categoryDiscounts = new HashMap<>();
}
