package videostore.horror;

import org.junit.Assert;
import org.junit.Test;
import videostore.horror.Movie.Category;


public class CustomerTest {

//    @Test
//    public void computesFrequentRenterPoints() {
//    }
    @Test
    public void characterizationTest() {
        Customer customer = new Customer("John Doe");
        customer.addRental(new Rental(new Movie("Star Wars", Category.NEW_RELEASE), 6));
        customer.addRental(new Rental(new Movie("Sofia", Category.CHILDREN), 7));
        customer.addRental(new Rental(new Movie("Smth", Category.NEW_RELEASE), 1));
        customer.addRental(new Rental(new Movie("Inception", Category.REGULAR), 5));
        
        String expected = "Rental Record for John Doe\n"
                + "	Star Wars	18.0\n"
                + "	Sofia	7.5\n"
                + "	Smth	3.0\n"
                + "	Inception	6.5\n"
                + "Amount owed is 35.0\n"
                + "You earned 5 frequent renter points";
        
        Assert.assertEquals(expected, new StatementGenerator().generateStatement(customer.getRentals(), customer.getName()));
    }
}
