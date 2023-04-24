
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
    System.out.println(getDiscountLine(new Customer()));
  }

  public static String getDiscountLine(Customer customer) {
    return Optional.ofNullable(customer.getMemberCard())
            .flatMap(Optional_Intro::computeDiscount)
            .map(d -> "You got a discount of %" + d.getGlobalPercentage())
            .orElse("");
  }

  private static Optional<Discount> computeDiscount(MemberCard card) {
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

  @Data
  public static class Discount {
    private final int globalPercentage;
    private Map<String, Integer> categoryDiscounts = new HashMap<>();
  }
}

