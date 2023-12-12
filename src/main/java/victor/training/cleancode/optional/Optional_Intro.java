
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
		System.out.println(getDiscountLine(new Customer()));
	}

	public static String getDiscountLine(Customer customer) {
		return customer.getMemberCard()
				.flatMap(Optional_Intro::computeDiscount) // te scapa de Optional<Optional<?>>
//				.map(Discount::getGlobalPercentage)
//				.map(p -> "You got a discount of %" + p)
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
	// private static Optional<Discount> computeDiscount(Optional<MemberCard> card) { // nu Optional<> param!!
	// in loc sa chemi o functie f(empty()) -> mai bine nu o chema de loc!
	private static Optional<Discount> computeDiscount(MemberCard card) {
		if (card == null) { // quick fix la 21:00 Vineri
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

