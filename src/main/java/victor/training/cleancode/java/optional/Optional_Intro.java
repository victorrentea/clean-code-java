
package victor.training.cleancode.java.optional;

import victor.training.cleancode.java.exception.model.Customer;
import victor.training.cleancode.java.exception.model.MemberCard;

import java.util.Map;

@SuppressWarnings("ConstantConditions")
public class Optional_Intro {
  public static void main(String[] args) {
    // test with 10 points or no MemberCard
    System.out.println(getDiscountLine(new Customer(
        new MemberCard("bar", 60))));
  }

  public static String getDiscountLine(Customer customer) {
    Discount discount = computeDiscount(customer.getMemberCard());
    return "You got a discount of %" + discount.globalPercentage();
  }

  private static Discount computeDiscount(MemberCard card) {
    if (card.getFidelityPoints() >= 100) {
      return new Discount(5, Map.of());
    }
    if (card.getFidelityPoints() >= 50) {
      return new Discount(3, Map.of());
    }
    return null;
  }

  public record Discount(int globalPercentage, Map<String, Integer> categoryDiscounts) {
  }
}

