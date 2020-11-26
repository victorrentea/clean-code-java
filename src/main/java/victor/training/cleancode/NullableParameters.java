package victor.training.cleancode;

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
			.map(p -> "Discount%: " + p)
			.orElse("");
	}
		
	private Optional<Integer> getApplicableDiscountPercentage(MemberCard card) {
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
		DiscountService discountService = new DiscountService();
		System.out.println(discountService.getDiscountLine(new Customer(new MemberCard(60))));
		System.out.println(discountService.getDiscountLine(new Customer(new MemberCard(10))));
		System.out.println(discountService.getDiscountLine(new Customer()));

		Data2 d = new Data2();
		d.x = 1;
		System.out.println(d.x);
		f2(d);
		System.out.println(d.x);
	}

	public static int f2(Data2 d) {return ++d.x;}

}

class Data2 {
	public int x;
}






// VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
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
