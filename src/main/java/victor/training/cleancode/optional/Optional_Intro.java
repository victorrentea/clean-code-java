
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
    System.out.println(getDiscountLine(new Customer(new MemberCard(1))));
  }

  public static String getDiscountLine(Customer customer) {
    //		Discount discount = Optional.ofNullable(computeDiscount(customer.getMemberCard()))
    //						.orElse(new Discount(0));

    Optional<Discount> discount = computeDiscount(customer.getMemberCard());
    if (discount.isPresent()) {
      return "You got a discount of %" + discount.get().getGlobalPercentage();
    } else {
      return "Earn more fidelity points for a discount";
    }
  }

  // Optional is supposed to be used for return types of functions called by many
  private static Optional<Discount> computeDiscount(MemberCard card) {
    if (card.getFidelityPoints() >= 100) {
      return Optional.of(new Discount(5));
    }
    if (card.getFidelityPoints() >= 50) {
      return Optional.of(new Discount(3));
    }
    return Optional.empty();
  }


  @Data
  public static class Discount {
    private final int globalPercentage;
    private Map<String, Integer> categoryDiscounts = new HashMap<>();
  }
}

