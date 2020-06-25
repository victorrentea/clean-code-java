package videostore.horror;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class StatementFormatterTest {

    @Test
    public void characterizationTest() {
        List<Rental> rentals = Arrays.asList(
            new Rental(new Movie("Star Wars", PriceCode.NEW_RELEASE), 6),
            new Rental(new Movie("Sofia", PriceCode.CHILDRENS), 7),
            new Rental(new Movie("Inception", PriceCode.REGULAR), 5));

        String expected = "Rental Record for John Doe\n"
                + "	Star Wars	18.0\n"
                + "	Sofia	7.5\n"
                + "	Inception	6.5\n"
                + "Amount owed is 32.0\n"
                + "You earned 4 frequent renter points";

        StatementFormatter formatter = new StatementFormatter();
        Assert.assertEquals(expected, formatter.createStatement("John Doe", rentals));
//        Assert.assertEquals(expected, formatter.createStatement(customer));
    }
}
