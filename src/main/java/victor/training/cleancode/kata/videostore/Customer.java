package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

class Customer
{
  @Getter
  private final String name;
  private final Map<Movie, Integer> rentals = new LinkedHashMap<>();
    // preserves order of elements TODO find a better way to store this

  public Customer( String name )
  {
    this.name = name;
  }

  public void addRental( Movie m, int d )
  {
    rentals.put( m, d );
  }

  public String statement()
  {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    // loop over each movie rental
    for ( Movie movie : rentals.keySet() )
    {
      double cost = 0;
      // determine amounts for every line
      int daysRented = rentals.get( movie );
      switch ( movie.priceCode() )
      {
        case Movie.REGULAR ->
        {
          cost += 2;
          if ( daysRented > 2 )
          {
            cost += ( daysRented - 2 ) * 1.5;
          }
        }
        case Movie.NEW_RELEASE -> cost += daysRented * 3;
        case Movie.CHILDREN ->
        {
          cost += 1.5;
          if ( daysRented > 3 )
          {
            cost += ( daysRented - 3 ) * 1.5;
          }
        }
      }
      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if ( movie.priceCode() == Movie.NEW_RELEASE && daysRented > 1 )
      {
        frequentRenterPoints++;
      }
      // show figures line for this rental
      result += "\t" + movie.title() + "\t" + cost + "\n";
      totalAmount += cost;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }
}