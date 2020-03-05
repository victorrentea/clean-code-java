package videostore.dirty;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class StatementGeneratorTest {

    @Test
    public void characterizationTest() {
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("Star Wars", Movie.Category.NEW_RELEASE), 6),
                new Rental(new Movie("Sofia", Movie.Category.CHILDREN), 7),
                new Rental(new Movie("Inception", Movie.Category.REGULAR), 5));

        StatementGenerator statementGenerator = new StatementGenerator();

        String expected = "Rental Record for John Doe\n"
                + "	Star Wars	18.0\n"
                + "	Sofia	7.5\n"
                + "	Inception	6.5\n"
                + "Amount owed is 32.0\n"
                + "You earned 4 frequent renter points";
        
        Assert.assertEquals(expected, statementGenerator.generateStatement("John Doe", rentals));
    }
}
