
package victor.training.cleancode.optional;

import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.MemberCard;

import java.util.Map;
import java.util.Optional;
//    try {
//      return "You got a discount of %" + discount.orElseThrow().globalPercentage();
//    } catch (NoSuchElementException e) { // abuz de execptii: nu e exceptie, e caz frecventa
//      return "No discount for you!";
//    }

@SuppressWarnings("ConstantConditions")
public class Optional_Intro {
  public static void main(String[] args) {
    // test with 10 points or no MemberCard
    System.out.println(getDiscountLine(new Customer(new MemberCard("bar", 60))));
    System.out.println(getDiscountLine(new Customer(new MemberCard("bar", 10))));
    System.out.println(getDiscountLine(new Customer()));
  }

  private static String getDiscountLine(Customer customer) {

    return customer.getMemberCard()
        .flatMap(Optional_Intro::computeDiscount)
        .map(d -> "You got a discount of %" + d.globalPercentage())
        .orElse("Earn more points to get a discount!");
  }

  private static Optional<Discount> computeDiscount(MemberCard card) {
    if (card == null) {
      return Optional.empty();
    }
    if (card.getFidelityPoints() >= 100) {
      return Optional.of(new Discount(5, Map.of()));
    }
    if (card.getFidelityPoints() >= 50) {
      return Optional.of(new Discount(3, Map.of()));
    }
//    return new Discount(0, Map.of()); // null obj pattern
    return Optional.empty();
  }

  public record Discount(int globalPercentage, Map<String, Integer> categoryDiscounts) {
  }
}

