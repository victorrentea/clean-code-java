
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
		System.out.println(getDiscountLine(new Customer(new MemberCard(10))));
	}

	public static String getDiscountLine(Customer customer) {
		return "You got a discount of %" +
			   getApplicableDiscountPercentage(customer.getMemberCard()).getGlobalPercentage();
	}

	private static Discount getApplicableDiscountPercentage(MemberCard card) {
		if (card.getFidelityPoints() >= 100) {
			return new Discount(5);
		}
		if (card.getFidelityPoints() >= 50) {
			return new Discount(3);
		}
		return Discount.NONE; // alternativa la optional, se numeste "Null Object Pattern"
			// reprezinta un obiect cu o stare care inseamna 'nimic' de fapt
	}
	@Data
	public static class Discount {
		public static final Discount NONE = new Discount(0);
		private final int globalPercentage;
		private Map<String, Integer> categoryDiscounts = new HashMap<>();
	}
}

