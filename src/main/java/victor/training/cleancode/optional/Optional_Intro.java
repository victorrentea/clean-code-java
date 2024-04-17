package victor.training.cleancode.optional;

import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.MemberCard;

import java.util.Map;

@SuppressWarnings("ConstantConditions")
public class Optional_Intro {
  public static void main(String[] args) {
    // test: 60, 10, no MemberCard
    System.out.println(getDiscountLine(new Customer(new MemberCard("bar", 60))));
    System.out.println(getDiscountLine(new Customer(new MemberCard("bar", 10))));
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
    return new Discount(0, Map.of()); // null object pattern= a non-null value that MEANS nothing
    // REQUIRES THE CALLER TO BE AWARE OF THIS CONVENTION
  }

  public record Discount(int globalPercentage, Map<String, Integer> categoryDiscounts) {
  }
}

