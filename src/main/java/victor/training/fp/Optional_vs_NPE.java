package victor.training.fp;

import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;

/* "I call it my billion-dollar mistake. 
 * It was the invention of the null reference in 1965..."
 *  -- Sir Charles Antony Richard  */

// Get a discount line to print in UI

class DiscountService {
	public String getDiscountLine(Customer customer) {
		return customer.getMemberCard()
			.map(card -> getApplicableDiscountPercentage(card))
			.map(discount -> "Discount%: " + discount)
			.orElse("");
	}
		
	private Integer getApplicableDiscountPercentage(MemberCard card) {
		if (card.getFidelityPoints() >= 100) {
			return 5;
		}
		if (card.getFidelityPoints() >= 50) {
			return 3;
		}
		return 0;
	}
		
	// test: 60, 10, no MemberCard
	public static void main(String[] args) {
		System.out.println(new DiscountService().getDiscountLine(new Customer(new MemberCard(60))));
		System.out.println(new DiscountService().getDiscountLine(new Customer(new MemberCard(10))));
		System.out.println(new DiscountService().getDiscountLine(new Customer()));
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
 //	@Nullable = IDE+SOnar hints
	public Optional<MemberCard> getMemberCard() {
		return Optional.ofNullable(memberCard);
	}
}

@Data
class MemberCard {
	private final int fidelityPoints;
}
