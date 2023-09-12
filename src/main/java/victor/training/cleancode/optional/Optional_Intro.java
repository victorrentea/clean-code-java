
package victor.training.cleancode.optional;

import lombok.Data;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.MemberCard;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("ConstantConditions")
public class Optional_Intro {
	public static void main(String[] args) {
		// test: 60, 10, no MemberCard
		System.out.println(getDiscountLine(new Customer(new MemberCard(60))));
		System.out.println(getDiscountLine(new Customer(new MemberCard(1))));
		System.out.println(getDiscountLine(new Customer(null))); // client nou
	}

	// + 100p pt ca functia ta incepe direct cu RETURN "expression functions" const f = (x,y) => x+y
	public static String getDiscountLine(Customer customer) {
		return computeDiscount(customer.getMemberCard())
				.map(Discount::getGlobalPercentage)
				.map(per -> "You got a discount of %" + per)
				.orElse("Daca aveai puncte de fidelitate puteai beneficia de un discount!");
	}

	private static Optional<Discount> computeDiscount(MemberCard card) {
		if (card == null) {
			return Optional.empty();
		}
		if (card.getFidelityPoints() >= 100) {
			return Optional.of(new Discount(5));
		}
		if (card.getFidelityPoints() >= 50) {
			return Optional.of(new Discount(3));
		}
		return Optional.empty();
	}
	@Data
	public static class Discount {
		private final int globalPercentage;
		private Map<String, Integer> categoryDiscounts = new HashMap<>();
	}
}

