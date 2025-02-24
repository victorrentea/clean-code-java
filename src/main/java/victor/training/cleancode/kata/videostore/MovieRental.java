package victor.training.cleancode.kata.videostore;

public record MovieRental(Movie movie, int daysRented)
{
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