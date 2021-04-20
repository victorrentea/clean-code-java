package videostore.horror;

import org.junit.Assert;
import org.junit.Test;
import videostore.horror.Movie.Category;

import java.util.List;

import static java.util.Arrays.asList;


public class StatementFormatterTest {

    @Test
    public void characterizationTest() {
        List<Rental> rentals = asList(
            new Rental(new Movie("Star Wars", Category.NEW_RELEASE), 6),
            new Rental(new Movie("Sofia", Category.CHILDREN), 7),
            new Rental(new Movie("Inception", Category.REGULAR), 5));

        String expected = "Rental Record for John Doe\n"
                + "	Star Wars	18.0\n"
                + "	Sofia	7.5\n"
                + "	Inception	6.5\n"
                + "Amount owed is 32.0\n"
                + "You earned 4 frequent renter points";

        StatementFormatter formatter = new StatementFormatter();
        Assert.assertEquals(expected, formatter.formatStatement("John Doe", rentals));
    }
}
