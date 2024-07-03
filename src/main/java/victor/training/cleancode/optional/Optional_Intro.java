
package victor.training.cleancode.optional;

import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.MemberCard;

import java.util.Map;
import java.util.Optional;

@SuppressWarnings("ConstantConditions")
public class Optional_Intro {
  public static void main(String[] args) {
    // test with 10 points or no MemberCard
    System.out.println(getDiscountLine(new Customer(new MemberCard("bar", 60))));
    System.out.println(getDiscountLine(new Customer(new MemberCard("bar", 1))));
  }

  public static String getDiscountLine(Customer customer) {
    Optional<Discount> discount = computeDiscount(customer.getMemberCard());
    if (discount.isPresent()) {
      return "You got a discount of %" + discount.get().globalPercentage();
    }
    return "Earn more points to get a discount"; // FOMO = Fear Of Missing Out
  }

  private static Optional<Discount> computeDiscount(MemberCard card) {
    if (card.getFidelityPoints() >= 100) {
      return Optional.of(new Discount(5, Map.of()));
    }
    if (card.getFidelityPoints() >= 50) {
      return Optional.of(new Discount(3, Map.of()));
    }
    return Optional.empty();
  }

  public record Discount(int globalPercentage, Map<String, Integer> categoryDiscounts) {
  }
}

