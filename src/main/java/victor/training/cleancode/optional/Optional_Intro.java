
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
		if (discount != null)
			return "You got a discount of %" +
				   discount.getGlobalPercentage();
		else return "";
	}

	private static Discount computeDiscount(MemberCard card) {
		if (card.getFidelityPoints() >= 100) {
			return new Discount(5);
		}
		if (card.getFidelityPoints() >= 50) {
			return new Discount(3);
		}
		return null;
	}

	@Data
	public static class Discount {
		private final int globalPercentage;
		private Map<String, Integer> categoryDiscounts = new HashMap<>();
	}
}

