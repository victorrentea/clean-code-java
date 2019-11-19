package videostore.dirty;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;


public class StatementGeneratorTest {

    @Test
    public void characterizationTest() {
        List<Rental> rentals = asList(
                new Rental(new Movie("Star Wars", Movie.Category.NEW_RELEASE), 6),
                new Rental(new Movie("Sofia", Movie.Category.CHILDREN), 7),
                new Rental(new Movie("Inception", Movie.Category.REGULAR), 5)
        );

        String expected = "Rental Record for John Doe\n"
                + "	Star Wars	18.0\n"
                + "	Sofia	7.5\n"
                + "	Inception	6.5\n"
                + "Amount owed is 32.0\n"
                + "You earned 4 frequent renter points";

        Assert.assertEquals(expected, new StatementGenerator().generateStatement("John Doe", rentals));
    }
}
