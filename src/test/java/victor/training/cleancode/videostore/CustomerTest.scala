package victor.training.cleancode.videostore

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CustomerTest {

  @Test
  def characterizationTest(): Unit = {
    val customer = new Customer("John Doe")
    customer.addRental(new Movie("Star Wars", Movie.NEW_RELEASE), 6)
    customer.addRental(new Movie("Sofia", Movie.CHILDRENS), 7)
    customer.addRental(new Movie("Inception", Movie.REGULAR), 5)
    customer.addRental(new Movie("Wicked", Movie.CHILDRENS), 3)

    val expected =
      """Rental Record for John Doe
        |	Star Wars	18.0
        |	Sofia	7.5
        |	Inception	6.5
        |	Wicked	1.5
        |Amount owed is 33.5
        |You earned 5 frequent renter points""".stripMargin

    assertThat(expected).isEqualToIgnoringNewLines(customer.statement)
    // assertEquals(expected, customer.statement()) // use this is above fails to compile
  }
}