
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
  }

  public static String getDiscountLine(Customer customer) {
    return getApplicableDiscountPercentage(customer.getMemberCard())
            .map(discount -> "You got a discount of %" + discount.getGlobalPercentage())
            .orElse("");
  }


  private static Optional<Discount> getApplicableDiscountPercentage(MemberCard card) {
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

