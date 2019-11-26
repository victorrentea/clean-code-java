package videostore.dirty;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class StatementBuilderTest {

    @Test
    public void characterizationTest() {
        StatementBuilder statementBuilder = new StatementBuilder();
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("Star Wars", MovieType.NEW_RELEASE), 6),
                new Rental(new Movie("Sofia", MovieType.CHILDREN), 7),
                new Rental(new Movie("Inception", MovieType.REGULAR), 5));

        String expected = "Rental Record for John Doe\n"
                + "	Star Wars	18.0\n"
                + "	Sofia	7.5\n"
                + "	Inception	6.5\n"
                + "Amount owed is 32.0\n"
                + "You earned 4 frequent renter points";
        Assert.assertEquals(expected, statementBuilder.buildStatement("John Doe", rentals));
    }
}
