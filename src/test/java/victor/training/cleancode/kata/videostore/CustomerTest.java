package victor.training.cleancode.kata.videostore;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static victor.training.cleancode.kata.videostore.MovieType.CHILDREN;
import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;
import static victor.training.cleancode.kata.videostore.MovieType.REGULAR;


public class CustomerTest {

    @Test
    public void characterizationTest() {
        Customer customer = new Customer("John Doe");
        customer.addRental(new Movie("Star Wars", NEW_RELEASE), 6);
        customer.addRental( new Movie("Sofia", CHILDREN ), 7);
        customer.addRental(new Movie("Inception", REGULAR), 5);
        customer.addRental( new Movie("Wicked", CHILDREN ), 3);

        String expected = """
            Rental Record for John Doe
            	Star Wars	18.0
            	Sofia	7.5
            	Inception	6.5
            	Wicked	1.5
            Amount owed is 33.5
            You earned 5 frequent renter points""";

        assertThat(expected).isEqualToIgnoringNewLines(customer.statement());
//        assertEquals(expected, customer.statement());// use this is above fails to compile
    }
}
