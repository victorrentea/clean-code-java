
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

  private static String getDiscountLine(Customer customer) {
//    return customer.getMemberCard()
//        .map(memberCard -> computeDiscount(memberCard)
//            .map(value -> "You got a discount of %" + value.globalPercentage())
//            .orElse("Buy more!")).orElse("Paranoicule!");
    return customer.getMemberCard()
        .flatMap(Optional_Intro::computeDiscount)
        .map(value -> "You got a discount of %" + value.globalPercentage())
        .orElse("No Discount");
  }

  private static Optional<Discount> computeDiscount(MemberCard card) {
//    if (card == null) { // paranoia programming
//      return Optional.empty();
//    }
    if (card.getFidelityPoints() >= 100) {
      return Optional.of(new Discount(5, Map.of()));
    }
    if (card.getFidelityPoints() >= 50) {
      return Optional.of(new Discount(3, Map.of()));
    }
    return Optional.empty();
  }

  protected record Discount(int globalPercentage, Map<String, Integer> categoryDiscounts) {
  }
}

