package victor.training.cleancode.kata.videostore;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.kata.videostore.movie.ChildrenRentedMovie;
import victor.training.cleancode.kata.videostore.movie.NewReleaseRentedMovie;
import victor.training.cleancode.kata.videostore.movie.RegularRentedMovie;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomerTest {

    @Test
    public void characterizationTest() {
        Customer customer = new Customer("John Doe");
        customer.addRental(new NewReleaseRentedMovie("Star Wars", 6));
        customer.addRental(new ChildrenRentedMovie("Sofia", 7));
        customer.addRental(new RegularRentedMovie("Inception", 5));
        
        String expected = """
            Rental Record for John Doe
            	Star Wars	18.0
            	Sofia	7.5
            	Inception	6.5
            Amount owed is 32.0
            You earned 4 frequent renter points""";
        
        assertEquals(expected, customer.statement());
    }
}
