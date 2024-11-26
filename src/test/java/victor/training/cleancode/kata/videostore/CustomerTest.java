package victor.training.cleancode.kata.videostore;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CustomerTest {

    @Test
    public void characterizationTest() {
        Customer customer = new Customer("John Doe");
        customer.addRental(new Movie("Star Wars", Movie.NEW_RELEASE), 6);
        customer.addRental(new Movie("Sofia", Movie.CHILDRENS), 7);
        customer.addRental(new Movie("Inception", Movie.REGULAR), 5);
        
        String expected = """
            Rental Record for John Doe
            	Star Wars	18.0
            	Sofia	7.5
            	Inception	6.5
            Amount owed is 32.0
            You earned 4 frequent renter points""";

        assertThat(expected).isEqualToIgnoringNewLines(customer.statement());
//        assertEquals(expected, customer.statement());// use this is above fails to compile
    }
}
