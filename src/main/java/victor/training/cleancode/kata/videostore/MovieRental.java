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
    int daysRented = daysRented();

    switch ( movie().movieType() )
    {
      case REGULAR:
        if ( daysRented > 2 )
        {
          return 2 + ( daysRented - 2 ) * 1.5;
        }

      case NEW_RELEASE:
        return daysRented * 3;

      case CHILDREN:
        if ( daysRented > 3 )
        {
          return 1.5 + ( daysRented - 3 ) * 1.5;
        }
    }

    return 1.5;
  }
}
