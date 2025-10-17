package victor.training.cleancode.kata.videostore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomerTest {

  @Test
  void characterizationTest() {
    Customer customer = new Customer("John Doe");
//    customer.addRental(new Movie("Star Wars", Movie.BABACI), 6);
    customer.addRental(new Movie("Star Wars", MovieCategory.NEW_RELEASE), 6);
    customer.addRental(new Movie("Sofia", MovieCategory.CHILDRENS), 7);
    customer.addRental(new Movie("Inception", MovieCategory.REGULAR), 5);
    customer.addRental(new Movie("Wicked", MovieCategory.CHILDRENS), 3);

    String expected = """
        Rental Record for John Doe
        	Star Wars	18.0
        	Sofia	7.5
        	Inception	6.5
        	Wicked	1.5
        Amount owed is 33.5
        You earned 5 frequent renter points""";

//    assertThat(customer.statement()).isEqualToIgnoringNewLines(expected);
    // if above line fails to compile, uncomment the next line:
    assertEquals(normalizeNewLines(expected), normalizeNewLines(customer.generateStatement()));
  }

  private String normalizeNewLines(String expected) {
    return expected.replaceAll("\\r\\n?", "\n");
  }

}
