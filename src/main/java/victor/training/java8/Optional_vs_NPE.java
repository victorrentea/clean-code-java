package victor.training.java8;

import lombok.Data;

import java.util.Optional;

import static java.util.Optional.ofNullable;

/* "I call it my billion-dollar mistake. 
 * It was the invention of the null reference in 1965..."
 *  -- Sir Charles Antony Richard  */

// Get a discount line to print in UI

class DiscountService {
	public String getDiscountLine(Customer customer) {
		return customer.getMemberCard()
			.flatMap(this::getApplicableDiscountPercentage)
			.map(per -> "Discount%: " + per)
			.orElse("");
	}
		
	public Optional<Integer> getApplicableDiscountPercentage(MemberCard card) {
		if (card == null) {
			return Optional.empty();
		}
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


// VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
//@Entity
class Customer {
	private MemberCard memberCard;
	public Customer() {
	}
	public Customer(MemberCard profile) {
		this.memberCard = profile;
	}
	public Optional<MemberCard> getMemberCard() {
		return ofNullable(memberCard);
	}
}

@Data
class MemberCard {
	private final int fidelityPoints;
}
