package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;

public record MovieRental(Movie movie, int daysRented)
{
  public boolean isEligibleForBonus()
  {
    return ( movie().movieType() == NEW_RELEASE ) && daysRented() > 1;
  }

  public String generateResult()
  {
    return "\t" + movie().title() + "\t" + computeAmount() + "\n";
  }

  public double computeAmount()
  {
    double amount = 0;
    int daysRented = daysRented();

    switch ( movie().movieType() )
    {
      case REGULAR:
        amount += 2;
        if ( daysRented > 2 )
        {
          amount += ( daysRented - 2 ) * 1.5;
        }
        return amount;
      case NEW_RELEASE:
        amount += daysRented * 3;
        return amount;
      case CHILDREN:
        amount += 1.5;
        if ( daysRented > 3 )
        {
          amount += ( daysRented - 3 ) * 1.5;
        }
        return amount;
    }

    return amount;
  }
}
