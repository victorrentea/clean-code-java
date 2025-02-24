package victor.training.cleancode.kata.videostore;

import victor.training.cleancode.kata.videostore.Movie.PriceCategory;

public record Rental( Movie movie, int days )
{
  public double cost()
  {
    return switch ( movie.priceCategory() )
    {
      case REGULAR -> getCostForRegular();

      case NEW_RELEASE -> days * 3;

      case CHILDREN -> getCostForChildren();

    };
  }

  private double getCostForRegular()
  {
    double cost = 2;
    if ( days > 2 )
    {
      cost += ( days - 2 ) * 1.5;
    }
    return cost;
  }

  private double getCostForChildren()
  {
    double cost = 1.5;
    if ( days > 3 )
    {
      cost += ( days - 3 ) * 1.5;
    }
    return cost;
  }

  @Override
  public String toString()
  {
    return String.format( "\t%s\t%s\n", movie().title(), cost() );
  }

  public int loyaltyPoints()
  {
    if ( movie().priceCategory() == PriceCategory.NEW_RELEASE && days() > 1 )
    {
      return 2;
    }
    return 1;
  }
}
