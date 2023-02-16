package videostore.horror;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import videostore.horror.Movie.PriceCode;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomerTest {

    @Test
    void throwsForNullTitle() {
        assertThatThrownBy(() -> new Movie(null, PriceCode.REGULAR));
    }

    //    @Test
    //    void throwFor..() {
    //        assertThatThrownBy(() -> new Movie(null, PriceCode.REGULAR));
    //    }

    @Test
    void throwsForNullPriceCode() {
        assertThatThrownBy(() -> new Movie("Asfd", null));
    }

    @Test
    public void characterizationTest() {
        Customer customer = new Customer("John Doe");
        customer.addRental(new Movie("Star Wars", PriceCode.NEW_RELEASE), 6);
        customer.addRental(new Movie("Sofia", PriceCode.CHILDREN), 7);
        customer.addRental(new Movie("Inception", PriceCode.REGULAR), 5);

        String expected = "Rental Record for John Doe\n"
                          + "	Star Wars	18.0\n"
                          + "	Sofia	7.5\n"
                          + "	Inception	6.5\n"
                          + "Amount owed is 32.0\n"
                          + "You earned 4 frequent renter points";

        assertEquals(expected, customer.statement());
    }
}
