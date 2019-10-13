package cleancode;

import lombok.Data;

/* "I call it my billion-dollar mistake. 
 * It was the invention of the null reference in 1965..."
 *  -- Sir Charles Antony Richard  */

// Get a discount line to print in UI

class DiscountService {
	public String getDiscountLine(Customer customer) {
		return "Discount%: " + getApplicableDiscountPercentage(customer.getMemberCard());
	}
		
	private Integer getApplicableDiscountPercentage(MemberCard card) {
		if (card.getFidelityPoints() >= 100) {
			return 5;
		}
		if (card.getFidelityPoints() >= 50) {
			return 3;
		}
		return null;
	}
		
	// test: 60, 10, no MemberCard
	public static void main(String[] args) {
		DiscountService discountService = new DiscountService();
		System.out.println(discountService.getDiscountLine(new Customer(new MemberCard(60))));
		System.out.println(discountService.getDiscountLine(new Customer(new MemberCard(10))));
	}
}








// VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
class Customer {
	private MemberCard memberCard;
	public Customer() {
	}
	public Customer(MemberCard profile) {
		this.memberCard = profile;
	}
	public MemberCard getMemberCard() {
		return memberCard;
	}
}

@Data
class MemberCard {
	private final int fidelityPoints;
}
