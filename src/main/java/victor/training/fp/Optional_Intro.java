
package victor.training.fp;

import lombok.Data;
import victor.training.cleancode.exception.model.Customer;
import victor.training.cleancode.exception.model.MemberCard;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("ConstantConditions")
public class Optional_Intro {

//	Optional<String> cev = Optional.empty(); // NU, ci lasi null in camp, asa e obiceiu

	public static void main(String[] args) {
		// test: 60, 10, no MemberCard
		System.out.println(getDiscountLine(new Customer(new MemberCard(60))));
	}

	public static String getDiscountLine(Customer customer) {
		return "Discount: " + getApplicableDiscountPercentage(customer.getMemberCard()).getGlobalPercentage();
	}

//	private static Discount getApplicableDiscountPercentage(Optional<MemberCard> card) {
				// NU pt ca tot poate sa-ti bage nullu in casa
				// NU pt ca asta inseamna ca ai doua flowuri (incalci SRP)

	private static Discount getApplicableDiscountPercentage(MemberCard card) {
//		MemberCard abuz = Optional.ofNullable(card).orElse(new MemberCard(10)); // NU creezi si termini Opt in acceasi functie!
//		MemberCard abuz = card != null ? card : new MemberCard(10);

		if (card.getFidelityPoints() >= 100) {
			return new Discount(5);
		}
		if (card.getFidelityPoints() >= 50) {
			return new Discount(3);
		}
		return null;
	}

//	public void method(int p, Optional<String> uneori) {
//		System.out.println("Chestii mereu " + p);
////		uneori.ifPresent(v -> {
////			ceva()
////		});ceva
//	}
////
//	private void ceva() {
//		ceva;
//	}
}




@Data
class Discount {
	private final int globalPercentage;
	private Map<String, Integer> categoryDiscounts = new HashMap<>();
}
