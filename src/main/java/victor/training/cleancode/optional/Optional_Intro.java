
package victor.training.cleancode.optional;

import lombok.Data;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.MemberCard;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("ConstantConditions")
public class Optional_Intro {
  public static void main(String[] args) {
    // test: 60, 10, no MemberCard
    System.out.println(getDiscountLine(new Customer(new MemberCard(60))));
    System.out.println(getDiscountLine(new Customer(new MemberCard(10))));
    System.out.println(getDiscountLine(new Customer(null)));
  }

  public static String getDiscountLine(Customer customer) {

//    if (customer.getMemberCard().isPresent()) {
//      MemberCard card = customer.getMemberCard().get();
//      return getApplicableDiscountPercentage(card)
//              .map(discount -> "You got a discount of %" + discount.getGlobalPercentage())
//              .orElse("");
//    } else {
//      return "";
//    }

    return customer.getMemberCard()
            .flatMap(Optional_Intro::getApplicableDiscountPercentage)
            .map(discount -> "You got a discount of %" + discount.getGlobalPercentage())
            .orElse("");


  }

// de ce e dubios sa accepti param asta Optional<> => de ce ma mai chemi daca nu imi dai nimic
  private static Optional<Discount> getApplicableDiscountPercentage(MemberCard card) {
//    if (card == null) {
//      return Optional.empty();
//    }
    if (card.getFidelityPoints() >= 100) {
      return Optional.of(new Discount(5));
    }
    if (card.getFidelityPoints() >= 50) {
      return Optional.of(new Discount(3));
    }
    return Optional.empty();
  }
//  enum {
//    SMALL_DISCOUNT(3), BIG_DISCOUNT
//  }

  @Data
  public static class Discount {
    private final int globalPercentage;
    private Map<String, Integer> categoryDiscounts = new HashMap<>();
  }
}

