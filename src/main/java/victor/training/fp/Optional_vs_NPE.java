package victor.training.fp;

import lombok.Data;

import java.util.Optional;

/* "I call it my billion-dollar mistake. 
 * It was the invention of the null reference in 1965..."
 *  -- Sir Charles Antony Richard  */

// Get a discount line to print in UI

class DiscountService {
	public String getDiscountLine(Customer customer) {
		return customer.getMemberCard()
			.flatMap(this::getApplicableDiscountPercentage)
			.map(i -> "Discount%: " + i)
			.orElse("");


//		if (d.isPresent()) { // code smell >>
//			System.out.println("Discount%: " + d.get());
//		}
	}


	/// in an inner API is much more acceptable to return a null.
	// GOD FORBID in a public one exposed to your core logic.
	private Optional<Integer> getApplicableDiscountPercentage(MemberCard card) {
		// return Optional is A SIGN that the function
		if (card.getFidelityPoints() >= 100) {
			return Optional.of(5);
		}
		if (card.getFidelityPoints() >= 50) {
			return Optional.of(3);
		}
		return Optional.empty();
	}
		
	// test: 60, 10, no MemberCard
	public static void main(String[] args) {
		System.out.println(new DiscountService().getDiscountLine(new Customer(new MemberCard(60))));
		System.out.println(new DiscountService().getDiscountLine(new Customer(new MemberCard(10))));
		System.out.println(new DiscountService().getDiscountLine(new Customer()));
	}
}


// VVVVVVVVV ==== supporting (HOLY DOMAIN MODEL!!) code ==== VVVVVVVVV
class Customer {
	private MemberCard memberCard;
	public Customer() {
	}
	public Customer(MemberCard profile) {
		this.memberCard = profile;
	}
	public Optional<MemberCard> getMemberCard() {
		return Optional.ofNullable(memberCard);
	}
}

@Data
class MemberCard {
	private final int fidelityPoints;
}
