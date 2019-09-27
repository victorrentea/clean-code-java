package videostore.dirty;

import static java.util.Arrays.asList;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class StatementMakerTest {

	@Test
	public void characterizationTest() {
		List<Rental> rentals = asList(new Rental(new Movie("Star Wars", Movie.Type.NEW_RELEASE), 6),
				new Rental(new Movie("Sofia", Movie.Type.CHILDREN), 7),
				new Rental(new Movie("Inception", Movie.Type.REGULAR), 5));

		String expected = "Rental Record for John Doe\n" + "	Star Wars	18.0\n" + "	Sofia	7.5\n"
				+ "	Inception	6.5\n" + "Amount owed is 32.0\n" + "You earned 4 frequent renter points";

		StatementMaker maker = new StatementMaker();
		Assert.assertEquals(expected, maker.makeStatement(rentals, "John Doe"));
	}
}
