
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
		System.out.println(getDiscountLine(new Customer(new MemberCard("bar", 60))));
		System.out.println(getDiscountLine(new Customer(new MemberCard("bar", 10))));
	}

	public static String getDiscountLine(Customer customer) {
		return computeDiscount(customer.getMemberCard())
				.map(value -> "You got a discount of %" + value.getGlobalPercentage())
				.orElse("Earn more fidelity points to benefit from a discount"); // FP
		// incearca sa eviti if(..isPresent()) {..} else {..} -> fa map in loc!
//		Optional<Discount> discount = computeDiscount(customer.getMemberCard());
				//.orElseThrow(() -> new IllegalStateException("N-ai puncte fidelitate"));// #3 exceptie mai faina
//    if (discount.isPresent()) {
//      return "You got a discount of %" + discount.get().getGlobalPercentage(); // #2 orElse(new Discount(0)) -> 0% cam naspa UX
//    } else {
//			return "Earn more fidelity points to benefit from a discount"; // #1 poezie de biz sa-l conving sa-si ia puncte
//		}
  }

	private static Optional<Discount> computeDiscount(MemberCard card) {
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

