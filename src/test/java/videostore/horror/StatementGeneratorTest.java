package videostore.horror;

import org.junit.Assert;
import org.junit.Test;
import videostore.horror.Movie.Category;

import java.util.Arrays;
import java.util.List;


public class StatementGeneratorTest {

    @Test
    public void characterizationTest() {
        List<Rental> rentals = Arrays.asList(
            new Rental(new Movie("Star Wars", Category.NEW_RELEASE), 6),
            new Rental(new Movie("Sofia", Category.CHILDREN), 7),
            new Rental(new Movie("Smth", Category.NEW_RELEASE), 1),
            new Rental(new Movie("Inception", Category.REGULAR), 5));

        String expected = "Rental Record for John Doe\n"
                + "	Star Wars	18.0\n"
                + "	Sofia	7.5\n"
                + "	Smth	3.0\n"
                + "	Inception	6.5\n"
                + "Amount owed is 35.0\n"
                + "You earned 5 frequent renter points";

        Assert.assertEquals(expected, new StatementGenerator().generateStatement("John Doe", rentals));

        // new MyEntity().setFirstName("fn").setLastName("ln");
        // dto = convert(entity) // prod code
        // assertequals("fn ln", dto.name);

        // ReflectionUtils.filLAllFields(entity) BAD
        // entity = ObjectMother.anEntity(); // factory method for test code.
        // entity.setFirstName("customExplicit");
        // prod
        // assertequals("customExplicit", dto.name);


    }
}
