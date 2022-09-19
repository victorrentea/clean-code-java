
package victor.training.fp;

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
        System.out.println(getDiscountLine(new Customer(new MemberCard(10))));
        System.out.println(getDiscountLine(new Customer()));
    }

    public static String getDiscountLine(Customer customer) {
        return customer.getMemberCard()
                        .map(MemberCard::getFidelityPoints)
                        .flatMap(Optional_Intro::getApplicableDiscountPercentage)
                        .map(disc -> "Discount: " + disc.getGlobalPercentage())
                        .orElse("");
    }

    public static Optional<Discount> getApplicableDiscountPercentage(int fidelityPoints) {
        if (fidelityPoints >= 100) {
            return Optional.of(new Discount(5));
        }
        if (fidelityPoints >= 50) {
            return Optional.of(new Discount(3));
        }
        return Optional.empty();
    }
}

@Data
class Discount {
    private final int globalPercentage;
    private Map<String, Integer> categoryDiscounts = new HashMap<>();
}
