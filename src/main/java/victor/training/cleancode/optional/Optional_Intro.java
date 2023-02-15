
package victor.training.cleancode.optional;

import lombok.Data;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.MemberCard;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ConstantConditions")
public class Optional_Intro {
	public static void main(String[] args) {
		// test: 60, 10, no MemberCard
		System.out.println(getDiscountLine(new Customer(new MemberCard(60))));
		System.out.println(getDiscountLine(new Customer(new MemberCard(1))));
	}

	public static String getDiscountLine(Customer customer) {
		Discount discount = computeDiscount(customer.getMemberCard());
		if (discount.getGlobalPercentage() != 0) // worse< here you might not have thought of checking for zero
			return "You got a discount of %" + discount.getGlobalPercentage();
		return "";
	}

	private static Discount computeDiscount(MemberCard card) {
		if (card.getFidelityPoints() >= 100) {
			return new Discount(5);
		}
		if (card.getFidelityPoints() >= 50) {
			return new Discount(3);
		}
		return new Discount(0); // Null-Object pattern = you create a dummy instnace instead of passing a nul laround
	}

	@Data
	public static class Discount {
		private final int globalPercentage;
		private Map<String, Integer> categoryDiscounts = new HashMap<>();
	}
}

