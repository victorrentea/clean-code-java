
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
    System.out.println(getDiscountLine(new Customer(new MemberCard("bar", 10))));
    System.out.println(getDiscountLine(new Customer()));
  }

  public static String getDiscountLine(Customer customer) {
//    return computeDiscount(customer.getMemberCard().get())
    return customer.getMemberCard()
        .flatMap(card -> computeDiscount(card))
        .map(Discount::globalPercentage)
        .map(p -> "You got a discount of %" + p)
        .orElse("Earn more points to get a discount!🤑");
  }

  // optional should be used only for return values, not for method parameters or field types.

  private static Optional<Discount> computeDiscount(MemberCard card) {
    if (card == null) { // guard condition, defensive programming = paranoia about invalid input
      // the best defense is a good offense: estinguish nulls in the first place or wrap them with Optional<>
      return Optional.empty();
    }
    if (card.getFidelityPoints() >= 100) {
      return Optional.of(new Discount(5, Map.of()));
    }
    if (card.getFidelityPoints() >= 50) {
      return Optional.of(new Discount(3, Map.of()));
    }
    // Null-Object Design Pattern (GoF)= return an object that does nothing instead of returning a null
    return Optional.empty();
  }

  public record Discount(int globalPercentage, Map<String, Integer> categoryDiscounts) {
    public static final Discount NONE = new Discount(0, Map.of());
  }
}

