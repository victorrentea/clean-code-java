package victor.training.cleancode.kata.videostore;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.kata.videostore.enums.MovieType;
import victor.training.cleancode.kata.videostore.movie.RentedMovie;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomerTest {

    @Test
    public void characterizationTest() {
        Customer customer = new Customer("John Doe");
        customer.addRental(RentedMovie.create("Star Wars", 6, MovieType.NEW_RELEASE));
        customer.addRental(RentedMovie.create("Sofia", 7, MovieType.CHILDREN));
        customer.addRental(RentedMovie.create("Inception", 5, MovieType.REGULAR));

        String expected = """
            Rental Record for John Doe
            	Star Wars	18.0
            	Sofia	7.5
            	Inception	6.5
            Amount owed is 32.0
            You earned 4 frequent renter points""";
        
        assertEquals(expected, customer.getClientRentalMoviesPrintableStatus());
    }
}
