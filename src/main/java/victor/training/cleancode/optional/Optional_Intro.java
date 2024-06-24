
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
    System.out.println(getDiscountLine(new Customer()));
  }

  public static String getDiscountLine(Customer customer) {
    return customer.getMemberCard()
        .flatMap(MemberCard::computeDiscount)
        .map(discount -> "You got a discount of %" + discount.globalPercentage())
        .orElse("If you had enough points on your member card, you could have gotten a discount.");
  }

  public record Discount(int globalPercentage, Map<String, Integer> categoryDiscounts) {
  }
}

