package victor.training.cleancode.kata.videostore;

public record MovieRental(Movie movie, int rentalDays)
{

  public double getPrice()
  {
    double thisAmount = 0;
    switch (movie().priceCode()) {
      case Movie.REGULAR:
        thisAmount += 2;
        if ( rentalDays() > 2)
          thisAmount += ( rentalDays() - 2) * 1.5;
        break;

      case Movie.NEW_RELEASE:
        thisAmount += rentalDays() * 3;
        break;

      case Movie.CHILDREN:
        thisAmount += 1.5;
        if ( rentalDays() > 3)
          thisAmount += ( rentalDays() - 3) * 1.5;
        break;

    }
    return thisAmount;
  }

  public String getStatement()
  {
    return "\t" + movie().title() + "\t" + getPrice() + "\n";
  }

  public int getFrequentRenterPoints()
  {
    int frequentRenterPoints = 0;
    // add frequent renter points
    frequentRenterPoints++;
    // add bonus for a two-day new release rental
    if ( movie().priceCode() == Movie.NEW_RELEASE && rentalDays() > 1 )
    {
      frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }
}
