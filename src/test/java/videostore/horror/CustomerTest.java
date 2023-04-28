package videostore.horror;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomerTest {

    @Test
    public void characterizationTest() {
        Customer customer = new Customer("John Doe");
        customer.addRental(new Movie("Star Wars", PriceFactor.NEW_RELEASE), 6);
        customer.addRental(new Movie("Sofia", PriceFactor.CHILDREN), 7);
        customer.addRental(new Movie("Inception", PriceFactor.REGULAR), 5);

        assertThat(customer.statement()).isEqualToIgnoringNewLines("""
                Rental Record for John Doe
                	Star Wars	18.0
                	Sofia	7.5
                	Inception	6.5
                Amount owed is 32.0
                You earned 4 frequent renter points""");
    }
}
